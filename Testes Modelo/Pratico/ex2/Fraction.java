public class Fraction {
    
    private int numerator;
    private int denominator = 1;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public Fraction add(Fraction frac) {
        int num = (numerator * frac.getDenominator()) + (frac.getNumerator() * denominator);
        int den = denominator * frac.getDenominator();

        return new Fraction(num, den);
    }
    
    public Fraction sub(Fraction frac) {
        int num = (numerator * frac.getDenominator()) - (denominator * frac.getNumerator());
        int den = denominator * frac.getDenominator();

        return new Fraction(num, den);
    }

    public Fraction mult(Fraction frac) {
        int num = numerator * frac.getNumerator();
        int den = denominator * frac.getDenominator();

        return new Fraction(num, den);
    }

    public Fraction div(Fraction frac) {
        int num = numerator * frac.getDenominator();
        int den = denominator * frac.getNumerator();

        return new Fraction(num, den);
    }

    public int gcd(int numerator, int denominator) {
        if (numerator % denominator == 0) {
            return denominator;
        }
        return gcd(denominator, numerator % denominator);
    }

    public void reduce() {
        int gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
    }

    @Override
    public String toString() {
        if (denominator == 1) {
            return numerator + "";
        } else {
            return numerator + "/" + denominator;
        }
    } 

    

}
