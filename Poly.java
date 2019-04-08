import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A class that defines a term inside a polynomial.
 * DO NOT MODIFY!!
 */
class Term {
    public int coef;
    public int exp;

    public Term(int coef, int exp) {
        this.coef = coef;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return coef + "x^" + exp;
    }
}

/**
 * A Polynomial ADT
 */
public class Poly {

    private Term[] terms;   // array of terms, not sorted
    private int next = 0;   // denotes next available slot & (term count)

    /**
     * Creates a new polynomial which can hold up to `termCount` `Term`s.
     * @param termCount
     */
    public Poly(int termCount) {
        terms = new Term[termCount];
    }

    /**
     * Creates a new polynomial with given terms as parameters.
     * @param termCount number of terms
     * @param terms array of terms to be added.
     */
    public Poly(int termCount, Term... terms) {
        this(termCount);

        if (termCount < terms.length)
            throw new IllegalArgumentException("termCount < terms.length");

        for (int i = 0; i < terms.length; i++) {
            addTerm(terms[i].coef, terms[i].exp);
        }
    }

    /**
     * Returns the degree of this polynomial.
     * @return degree of polynomial
     */
    public int degree() {
    	int biggest = 0;
        for (int i = 0; i < this.terms.length; i++) {
            if (this.terms[i] != null && this.terms[i].exp >= biggest)
            	biggest = this.terms[i].exp;
        }
        return biggest;
    }

    /**
     * Returns the number of terms
     * @return the number of terms
     */
    public int getTermCount() {
        return next;
    }

    /**
     * Insert a new term into a given polynomial.
     * @param coef coefficient
     * @param exponent exponent
     */
    public void addTerm(int coef, int exponent) {

        terms[getTermCount()] = new Term(coef,exponent);
        next ++; 
    }

    /**
     * Adds the target polynomial object with the one given as a parameter.
     * As a result, the returned polynomial object will eventually represent
     * the sum of two polynomials (C = A.add(B). Note that A should not be
     * modified as a result of this operation.
     * @param other a polynomial
     * @return a new polynomial (`other` + `this`)
     */
    public Poly add(Poly other) {

        Poly result;
        
        if (other.degree() >= this.degree())
        	result = new Poly(other.degree()+1);
        else
        	result = new Poly(this.degree()+1);
        
        System.out.println(result.terms.length);
        for (int k = 0; k < result.terms.length; k++) {
        	result.addTerm(0, result.terms.length-k-1);
            for (int i = 0; i < this.terms.length; i++) {
        		if (this.terms[i] != null && result.terms[k].exp == this.terms[i].exp)
        			result.terms[k].coef += this.terms[i].coef;
            }
        	for (int j = 0; j < other.terms.length; j++) {
        		if (other.terms[j] != null && result.terms[k].exp == other.terms[j].exp)
        			result.terms[k].coef += other.terms[j].coef;
        	}
        }

        return result;
    }

    /**
     * Multiply the target polynomial object with the one given as a parameter.
     * As a result, the returned polynomial object will eventually represent
     * the product of two polynomials (C = A.mutiply(B). Note that A should not be
     * modified as a result of this operation.
     * @param other a polynomial
     * @return a new polynomial (`other` * `this`)
     */
    public Poly mult(Poly other) {

    	int r_degree = 0;
    	if (other.degree() == 0)
    		r_degree = this.degree();
    	else if (this.degree() == 0)
    		r_degree = other.degree();
    	else
    	    r_degree = other.degree()+this.degree();
    	
        Poly result = new Poly(r_degree+1);
        
        for (int i = 0; i < result.terms.length; i++) {
        	Term tmp = new Term (0,r_degree-i);
        	for (int j = 0; j < other.terms.length; j++) {
        		for (int k = 0; k < this.terms.length; k++) {
        			if (other.terms[j] != null && this.terms[k] != null && tmp.exp == other.terms[j].exp + this.terms[k].exp)
        				tmp.coef += other.terms[j].coef * this.terms[k].coef;
        		}
        	}
        	if (tmp.coef != 0)
        		result.addTerm(tmp.coef, tmp.exp);
        }
        return result;
    }

    @Override
    public String toString() {
        // Sample code ... you can freely modify this code if necessary
        Arrays.sort(terms, 0, next, (a, b) -> b.exp - a.exp);
        return Arrays.stream(terms)
                     .filter(i -> i != null)
                     .map(i -> i.toString())
                     .collect(Collectors.joining(" + "));
    }

    public static void main(String... args) {
/*        Poly poly1 = new Poly(4);
        poly1.addTerm(1,3);
        poly1.addTerm(2,2);
        poly1.addTerm(3,1);
        poly1.addTerm(4,0);
        System.out.println(poly1);

        Poly poly2 = new Poly(4);
        poly2.addTerm(2,1);
        poly2.addTerm(1,0);
        poly2.addTerm(3,2);
        poly2.addTerm(4,3);
        System.out.println(poly2);

        Poly poly3 = poly1.add(poly2);
       System.out.println(poly3);

        Poly poly4 = poly1.mult(poly2);
        System.out.println(poly4); */
        
        Poly poly1 = new Poly(4);
        poly1.addTerm(1, 3);
        poly1.addTerm(2, 2);
        poly1.addTerm(3, 1);
        poly1.addTerm(4, 0);

        Poly poly2 = new Poly(4);
        poly2.addTerm(2, 1);
        poly2.addTerm(1, 0);
        poly2.addTerm(4, 3);

        Poly poly3 = poly1.mult(poly2);
        System.out.println(poly3);
    }
}
