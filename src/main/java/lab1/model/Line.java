package lab1.model;

public final class Line {
    public final double A;
    public final double B;
    public final double C;

    public Line(double a, double b, double c) {
        this.A = a;
        this.B = b;
        this.C = c;
    }

    @Override
    public String toString() {
        return String.format("%.10f*x + %.10f*y + %.10f = 0", A, B, C);
    }
}