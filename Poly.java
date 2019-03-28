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

        // you code goes here
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
        // your code goes here
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

        // you code goes here
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

        // you code goes here
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

        // you code goes here
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
        Poly poly1 = new Poly(4);
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
        System.out.println(poly4);
    }
}
