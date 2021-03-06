/**
 *  * Student: Weifeng Zhao
 *   */
public class A1LinkedListZW {

    public static void main(String argc[]) throws Exception {
        Linkedlist < Integer > sl = new Linkedlist < > ();
        DLinkedList < String > dl = new DLinkedList < > ();
        PolynomialLinkedlist sum, prod;
        for (int i = 1000; i > 0; i -= 3) sl.add(i);

        try {
            sl.insert(111, sl.getNode(50), sl.getNode(51));
            if (sl.detectLoop()) System.out.println("Loop!");
            else System.out.println("No loop.");


            sl.insert(123, sl.getNode(51), sl.getNode(50));
            if (sl.detectLoop()) System.out.println("Loop!");
            else System.out.println("No loop.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        dl.add("Three", 0);
        dl.add("Five", 1);
        dl.add("One", 0);
        dl.add("Two", 1);
        dl.add("Four", 3);
        dl.print();

        PolynomialLinkedlist p1 = new PolynomialLinkedlist(2, 3);
        PolynomialLinkedlist p2 = new PolynomialLinkedlist(3, 2);
        PolynomialLinkedlist p3 = p1.add(p2);
        p1 = new PolynomialLinkedlist(3, 2);
        p2 = new PolynomialLinkedlist(1, 0);
        PolynomialLinkedlist p4 = p1.add(p2);
        sum = p3.add(p4);
        prod = p3.multiply(p4);
        sum.print();
        prod.print();
        p1.print();
        p2.print();
    }
}

class Linkedlist < E > {
    private static class Node < E > {
        private E element;
        private Node < E > next;
        public Node(E e, Node < E > n) {
            element = e;
            next = n;
        }
        public E getE() {
            return element;
        }
        public Node < E > getNext() {
            return next;
        }
        public void setE(E e) {
            element = e;
        }
        public void setNext(Node < E > n) {
            next = n;
        }
    }
    private Node < E > head;
    public Linkedlist() {
        head = null;
    }

    public boolean detectLoop() {

        Node < E > fast, slow;
        fast = head;
        slow = head;

        while (fast != null && slow != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public void add(E e) {
        Node < E > temp = new Node < > (e, head);
        head = temp;
    }
    public void insert(E e, Node < E > p, Node < E > n) {
        p.setNext(new Node < > (e, n));
    }
    public Node < E > getNode(int i) throws Exception {
        Node < E > temp = head;
        while (i > 0) {
            if (temp == null) throw new Exception("Out of bound");
            temp = temp.getNext();
            i--;
        }
        return temp;
    }
}


class DLinkedList < E > {

    private static class DNode < E > {

        private E element;
        private DNode < E > prev;
        private DNode < E > next;

        public DNode(E e) {
            this(e, null, null);
        }

        public DNode(E e, DNode < E > p, DNode < E > n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getE() {
            return element;
        }

        public DNode < E > getPrev() {
            return prev;
        }
        public DNode < E > getNext() {
            return next;
        }
        public void setE(E e) {
            element = e;
        }
        public void setPrev(DNode < E > p) {
            prev = p;
        }
        public void setNext(DNode < E > n) {
            next = n;
        }
    }

    private DNode < E > header;
    private DNode < E > trailer;
    private int size;


    public DLinkedList() {
        header = new DNode < E > (null);
        trailer = new DNode < E > (null, header, null);
        header.setNext(trailer);
        size = 0;
    }

    public void print() {
        DNode < E > temp = header.getNext();
        while (temp != trailer) {
            System.out.print(temp.getE().toString() + ", ");
            temp = temp.getNext();
        }
        System.out.println();
    }

    @SuppressWarnings("unchecked")
    public void add(String s, int index) throws Exception {
        DNode currNode = header;
        while (index > 0) {
            if (currNode == null) {
                throw new Exception();
            }
            currNode = currNode.getNext();
            index--;
        }

        DNode < String > temp = new DNode < String > (s, currNode, currNode.getNext());
        currNode.getNext().setPrev(temp);
        currNode.setNext(temp);
        size++;

    }
}


class PolynomialLinkedlist {
    private static class PNode {
        private int coe;
        private int exp;
        private PNode next;
        public PNode(int c, int e) {
            this(c, e, null);
        }
        public PNode(int c, int e, PNode n) {
            coe = c;
            exp = e;
            next = n;
        }
        public void setCoe(int c) {
            coe = c;
        }
        public void setExp(int e) {
            exp = e;
        }
        public void setNext(PNode n) {
            next = n;
        }
        public int getCoe() {
            return coe;
        }
        public int getExp() {
            return exp;
        }
        public PNode getNext() {
            return next;
        }
    }
    private PNode first;
    private PNode last;
    public PolynomialLinkedlist() {
        first = last = null;
    }
    public PolynomialLinkedlist(int c, int e) {
        PNode tempn = new PNode(c, e);
        first = last = tempn;
    }

    public PolynomialLinkedlist add(PolynomialLinkedlist p) {

        PolynomialLinkedlist result = new PolynomialLinkedlist();
        PNode p1 = this.first;
        PNode p2 = p.first;

        while (!(p1 == null && p2 == null)) {
            if (p1 == null) {
                if (result.last == null) {
                    result.first = new PNode(p2.coe, p2.exp);
                    result.last = result.first;
                    p2 = p2.next;
                } else {
                    result.last.next = new PNode(p2.coe, p2.exp);
                    result.last = result.last.next;
                    p2 = p2.next;
                }
            } else if (p2 == null) {
                if (result.last == null) {
                    result.first = new PNode(p1.coe, p1.exp);
                    result.last = result.first;
                    p1 = p1.next;
                } else {
                    result.last.next = new PNode(p1.coe, p1.exp);
                    result.last = result.last.next;
                    p1 = p1.next;
                }
            } else {
                if (p1.exp > p2.exp) {
                    if (result.last == null) {
                        result.first = new PNode(p1.coe, p1.exp);
                        result.last = result.first;
                        p1 = p1.next;
                    } else {

                        result.last.next = new PNode(p1.coe, p1.exp);
                        result.last = result.last.next;
                        p1 = p1.next;
                    }
                } else if (p1.exp < p2.exp) {
                    if (result.last == null) {
                        result.first = new PNode(p2.coe, p2.exp);
                        result.last = result.first;
                        p2 = p2.next;
                    } else {
                        result.last.next = new PNode(p2.coe, p2.exp);
                        result.last = result.last.next;
                        p2 = p2.next;
                    }
                } else {
                    if (result.last == null) {
                        result.first = new PNode(p1.coe + p2.coe, p1.exp);
                        result.last = result.first;

                        p1 = p1.next;
                        p2 = p2.next;
                    } else {
                        result.last.next = new PNode(p1.coe + p2.coe, p1.exp);
                        result.last = result.last.next;

                        p1 = p1.next;
                        p2 = p2.next;
                    }
                }
            }
        }
        return result;
    }

    public PolynomialLinkedlist multiply(PolynomialLinkedlist p) {
        PolynomialLinkedlist result = new PolynomialLinkedlist();
        PNode p1 = this.first;
        while (p1 != null) {
            PNode p2 = p.first;
            while (p2 != null) {
                result = result.add(new PolynomialLinkedlist(p1.coe * p2.coe, p1.exp + p2.exp));
                p2 = p2.next;
            }
            p1 = p1.next;
        }
        return result;
    }

    public void print() {
        if (first == null) {
            System.out.println();
            return;
        }
        PNode temp = first;
        String ans = "";
        while (temp != null) {
            if (temp.getCoe() > 0) {
                if (temp != first) ans = ans + " + ";
                ans = ans + temp.getCoe();
            } else if (temp.getCoe() < 0) ans = ans + " - " + temp.getCoe() * -1;
            if (temp.getExp() != 0) {
                ans = ans + "X^" + temp.getExp();
            }
            temp = temp.getNext();
        }
        System.out.println(ans);
    }
}

