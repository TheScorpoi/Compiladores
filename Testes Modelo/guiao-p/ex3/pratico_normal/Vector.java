import java.util.ArrayList;

public class Vector {

    private ArrayList<Double> vectorList = new ArrayList<>();

    public Vector(double number) {
        vectorList.add(number);
    }

    public void add(double value) {
        vectorList.add(value);
    }

    public int vectorLength() {
        return vectorList.size();
    }

    public ArrayList<Double> vectorList() {
        return vectorList;
    }

    public void unario() {
        ArrayList<Double> list = new ArrayList<>();
        for (Double number : vectorList) {
            list.add(number);
        }
        vectorList.removeAll(vectorList);

        int i = 0;
        for (Double number : list) {
            number *= -1;
            vectorList.add(i, number);
            i++;

        }
    }

    public Vector sum(Vector vec) {
        Vector vector = null;
        ArrayList<Double> list = new ArrayList<>();

        for (Double number : vec.vectorList()) {
            list.add(number);
        }

        double sum = 0;
        if (vec.vectorLength() == vectorList.size()) {
            for (int i = 0; i < vectorLength(); i++) {
                sum = list.get(i) + vectorList.get(i);
                if (i == 0) {
                    vector = new Vector(sum);
                } else {
                    vector.add(sum);
                }
            }
        }
        return vector;
    }

    public Vector sub(Vector vec) {
        Vector vector = null;
        ArrayList<Double> list = new ArrayList<>();

        for (Double number : vec.vectorList()) {
            list.add(number);
        }

        double sub = 0;
        if (vec.vectorLength() == vectorList.size()) {
            for (int i = 0; i < vectorLength(); i++) {
                sub = vectorList.get(i) - list.get(i);
                if (i == 0) {
                    vector = new Vector(sub);
                } else {
                    vector.add(sub);
                }
            }
        }
        return vector;
    }

    public Vector prodInter(Vector vec) {
        Vector vector = null;
        ArrayList<Double> list = new ArrayList<>();

        for (Double number : vec.vectorList()) {
            list.add(number);
        }
        double prod = 0;
        if (vec.vectorLength() == vectorList.size()) {
            for (int i = 0; i < vectorLength(); i++) {
                prod = vectorList.get(i) * list.get(i);
                if (i == 0) {
                    vector = new Vector(prod);
                } else {
                    vector.add(prod);
                }
            }
        }
        double prodInter = 0;
        for (Double number : vector.vectorList()) {
            prodInter += number;
        }
        vector.vectorList().removeAll(vector.vectorList());
        vector.vectorList().add(prodInter);

        return vector;
    }

    public Vector mult(Vector vec) {
        Vector vector = null;
        ArrayList<Double> list = new ArrayList<>();

        for (Double number : vec.vectorList()) {
            list.add(number);
        }

        double sub = 0;
        if (vectorList.size() == 1) {
            for (int i = 0; i < list.size(); i++) {
                sub = vectorList.get(0) * list.get(i);
                if (i == 0) {
                    vector = new Vector(sub);
                } else {
                    vector.add(sub);
                }
            }
        }
        return vector;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (Double number : vectorList) {
            sb.append(number + ",");
        }
        sb.setLength(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
