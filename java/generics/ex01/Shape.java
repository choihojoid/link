package generics.ex01;

class Shape implements Comparable<Shape> {

    private int size;

    public Shape(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public int compareTo(Shape other) {
        return Integer.compare(this.size, other.size);
    }

    @Override
    public String toString() {
        return "Shape{" + "size=" + size + '}';
    }

}
