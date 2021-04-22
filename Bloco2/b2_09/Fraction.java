
public class Fraction {

    private int numerator;
    private int denominator;


    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public Fraction sume(Fraction f) {
        int numerator_tmp = f.getNumerator();
        int denominator_tmp = f.getDenominator();

        int num2 = greatestCommomDivisor(this.denominator, denominator_tmp);
        num2 = (this.denominator * denominator_tmp) / num2;
        int num1 = this.numerator * (num2 / this.denominator) + numerator_tmp + (num2 / denominator_tmp);
        return new Fraction(num1, num2);
    }

    public Fraction subtraction(Fraction f) {
        int numerator_tmp = f.getNumerator();
        int denominator_tmp = f.getDenominator();

        int num2 = greatestCommomDivisor(this.denominator, denominator_tmp);
        num2 = (this.denominator * denominator_tmp) / num2;
        int num1 = this.numerator * (num2 / this.denominator) - numerator_tmp + (num2 / denominator_tmp);
        return new Fraction(num1, num2);
    }
    
    public Fraction multiplication(Fraction f) {
        int numerator_tmp = f.getNumerator();
        int denominator_tmp = f.getDenominator();

        int num1 = this.numerator * numerator_tmp;
        int num2 = this.denominator * denominator_tmp;
        return new Fraction(num1, num2);
    }

    public Fraction division(Fraction f) {
        int numerator_tmp = f.getNumerator();
        int denominator_tmp = f.getDenominator();

        Fraction fractionTmp = new Fraction(denominator_tmp, numerator_tmp);
        return multiplication(fractionTmp);
    }

    public Fraction reduce() {
        int divideBy = greatestCommomDivisor(numerator, denominator);
        int num1 = numerator / divideBy;
        int num2 = denominator / divideBy;
        return new Fraction(num1, num2);
    }

    public int greatestCommomDivisor(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }
        return greatestCommomDivisor(num2, num1%num2);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + denominator;
        result = prime * result + numerator;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Fraction other = (Fraction) obj;
        if (denominator != other.denominator)
            return false;
        if (numerator != other.numerator)
            return false;
        return true;
    }

    @Override
    public String toString() {
        if (denominator == 1) {
            return String.valueOf(numerator);
        }
        return numerator + "/" + denominator; 
    }
}