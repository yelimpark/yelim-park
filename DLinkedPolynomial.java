/*
 * 짤 2019 CSE2010 HW #2
 *
 * You can freely modify this class except the signatures of the public methods!!
 */

import java.util.Arrays;
import java.util.stream.Collectors;

class Term {
    /*
     * Public field is a bad idea. But, for the sake of simplicity .....
     */
    public double   coeff;  // coefficient
    public int      expo;   // exponent

    public Term(double coeff, int expo) {
        this.coeff = coeff;
        this.expo = expo;
    }

    public static int compare(Term t1, Term t2) {
        if (t1.expo > t2.expo)
            return 1;
        if (t1.expo < t2.expo)
            return -1;
        return 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Term))
            return false;
        final Term term = (Term) o;
        return Double.compare(term.coeff, coeff) == 0 &&
            expo == term.expo;
    }
}

/*
 * class DLinkedPolynomial
 */
public class DLinkedPolynomial implements Polynomial {

    private DLinkedList<Term> list = null;

    public DLinkedPolynomial() {
    	list = new DLinkedList<Term>();
    }

    @Override
    public int getDegree() {
    	if (list.size() == 0)
    		return 0;
    	return list.getFirstNode().getInfo().expo;
    }

    @Override
    public double getCoefficient(final int expo) {
    	Node<Term> cur = new Node<Term>(null);
    	for (int i=0; i<list.size(); i++)
    	{
    		if (i == 0)
    			cur = list.getFirstNode();
    		else
    			cur = list.getNextNode(cur);
    		if(cur.getInfo().expo == expo)
    			return cur.getInfo().coeff;
    	}
		return 0;
    }

    @Override
    public Polynomial add(final Polynomial p) {
    	Polynomial result = new DLinkedPolynomial();
    	int rDegree = 0;
    	if (this.getDegree() >= p.getDegree())
    		rDegree = this.getDegree();
    	else
    		rDegree = p.getDegree();
    	for (int i=0; i<=rDegree; i++)
    	{
    		if (this.getCoefficient(rDegree-i) + p.getCoefficient(rDegree-i)!=0)
    		    result.addTerm(this.getCoefficient(rDegree-i) + p.getCoefficient(rDegree-i), rDegree-i);
    	}
    	return result;
    }

    @Override
    public Polynomial mult(final Polynomial p) {
    	Polynomial result = new DLinkedPolynomial();
    	
    	for (int i=0; i<=this.getDegree(); i++)
    	{
    		for (int j=0; j<=p.getDegree(); j++)
    		{
    			if (this.getCoefficient(this.getDegree()-i) * p.getCoefficient(p.getDegree()-j) != 0)
                result.addTerm(this.getCoefficient(this.getDegree()-i) * p.getCoefficient(p.getDegree()-j), (this.getDegree()-i)+(p.getDegree()-j));
    		}
    	}
    	return result;
    }

    @Override
    public void addTerm(final double coeff, int expo) {
    	Node<Term> other = new Node<Term>(new Term(coeff,expo));
    	if (list.size() == 0)
    		list.addFirst(other);
    	else
    	{
    		Node<Term> cur = new Node<Term>(null) ;
        	for (int i=0; i<list.size(); i++) 
        	{
            	if (i == 0)
            		cur = list.getFirstNode();
            	else
            		cur = list.getNextNode(cur);
            	
        		if(cur.getInfo().expo < expo)
        		{
        			list.addBefore(cur,other);
        			break;
        		}
        		else if (cur.getInfo().expo == expo)
        		{
        			cur.setInfo(new Term(cur.getInfo().coeff + coeff,expo));
        			break;
        		}
        	}
        	if(list.getLastNode().getInfo().expo > expo)
        		list.addLast(other);
    	}
    }

    @Override
    public void removeTerm(final int expo) {
    	boolean exist = false;
    	Node<Term> cur = new Node<Term>(null);
    	for (int i=0; i<list.size(); i++)
    	{
    		if (i == 0)
    			cur = list.getFirstNode();
    		else
    			cur = list.getNextNode(cur);
    		if(cur.getInfo().expo == expo)
    		{
    			list.remove(cur);
    			exist = true;
    		}
    	}
    	if (exist == false)
    		throw new NoSuchTermExistsException();
    }

    @Override
    public double evaluate(final double val) {
    	double result = 0;
    	for (int i=0; i<=this.getDegree(); i++)
    		result += this.getCoefficient(this.getDegree()-i) * Math.pow(val, this.getDegree()-i);
		return result;
    }

    @Override
    public int termCount() {
    	return list.size();
    }

    @Override
    public String toString() {
        if (list.isEmpty())
            return "Empty Polynomial";
        else {
            String[] terms = new String[termCount()];
            int i = 0;
            Node<Term> current = list.getFirstNode();
            do {
                terms[i++] = current.getInfo().coeff + "x^" + current.getInfo().expo;
                current = list.getNextNode(current);
            } while (current != null);
            return Arrays.stream(terms).collect(Collectors.joining("+"));
        }
    }

}

