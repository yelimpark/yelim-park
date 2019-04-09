/*
 * © 2019 CSE2010 HW #2
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

    }

    @Override
    public int getDegree() {

    }

    @Override
    public double getCoefficient(final int expo) {

    }

    @Override
    public Polynomial add(final Polynomial p) {

    }

    @Override
    public Polynomial mult(final Polynomial p) {

    }

    @Override
    public void addTerm(final double coeff, int expo) {

    }

    @Override
    public void removeTerm(final int expo) {

    }

    @Override
    public double evaluate(final double val) {

    }

    @Override
    public int termCount() {

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

