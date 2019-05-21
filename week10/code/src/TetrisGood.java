//import java.util.Queue;
//
//public class TetrisGood {
//}
//
//class Node {
//    int c;
//    Node l, r;
//    int left, right;
//    int markValue;
//
//    boolean isLeaf() {
//        return left == right;
//    }
//
//    Node(int v, int ind) {
//        resetMark();
//        c = v;
//        left = right = ind;
//    }
//
//    Node(Node a, Node b) {
//        resetMark();
//        c = a.c + b.c;
//        l = a;
//        r = b;
//        left = a.left;
//        right = b.right;
//    }
//
//    Node(int n, int left, int right) {
//        if (left > right)
//            return;
//        c = 0;
//        resetMark();
//        if (left == right) {
//            left = right = left;
//            return;
//        }
//        n = (1 << (int) Math.ceil(Math.log(n)/Math.log(2));
//        right = Math.max(right, n - 1);
//        left = left;
//        right = right;
//        l = new Node(n / 2, left, ((left + right) / 2));
//        r = new Node(n / 2, ((left + right) / 2) + 1, right);
//    }
//
//    Node(int[] arr) {
//        resetMark();
//        int i;
//        Queue<Node> q;
//        int n = arr.length;
//        int m = 1 << (int) Math.ceil(Mathlog2(n));
//        for (i = 0; i < m; i += 2) {
//            Node * a, *b;
//            if (i < n)
//                a = new Node(arr[i], i);
//            else
//                a = new Node(0, i);
//            if (i + 1 < n)
//                b = new Node(arr[i + 1], i + 1);
//            else
//                b = new Node(0, i + 1);
//
//            q.push(new Node(a, b));
//        }
//        while (!q.empty()) {
//            Node * a,*b;
//            a = q.front();
//            q.pop();
//            if (q.empty()) {
//                c = a -> c;
//                l = a -> l;
//                r = a -> r;
//                left = a -> left;
//                right = a -> right;
//                break;
//            }
//            b = q.front();
//            q.pop();
//            q.push(new Node(a, b));
//        }
//    }
//
//    boolean inInterval(int ind) {
//        return ind >= left && ind <= right;
//    }
//
//    void add(int left, int right, int value) {
//        propagate();
//        c += value * (right - left + 1);
//        if (isLeaf())
//            return;
//        updateMarkInters(left, right, value);
//    }
//
//    int getSum(int left, int right =-1) {
//        propagate();
//        if (right == -1) right = left;
//        if (left == left && right == right)
//            return c;
//        int s = 0, lInt, rInt;
//        l -> getIntersection( & lInt, &rInt, left, right);
//        if (lInt != -1)
//            s += l -> getSum(lInt, rInt);
//
//        r -> getIntersection( & lInt, &rInt, left, right);
//        if (lInt != -1)
//            s += r -> getSum(lInt, rInt);
//        return s;
//    }
//
//	~
//
//    Node() {
//        if (!isLeaf())
//            delete l, r;
//    }
//
//    private:
//
//    void getIntersection(int*left, int *right, int a, int b) {
//		*left = max(left, a);
//        if (*left > right)
//        {
//			*left = *right = -1;
//            return;
//        }
//		*right = min(right, b);
//        if (*right<left)
//        {
//			*right = *left = -1;
//            return;
//        }
//    }
//
//    void resetMark() {
//        markValue = 0;
//    }
//
//    void updateMarkInters(int left, int right, int value) {
//        if (left == left && right == right || isLeaf()) {
//            updateMark(left, right, value);
//            return;
//        }
//        int il, ir;
//        l -> getIntersection( & il, &ir, left, right);
//        if (il != -1)
//            l -> updateMarkInters(il, ir, value);
//        r -> getIntersection( & il, &ir, left, right);
//        if (il != -1)
//            r -> updateMarkInters(left, right, value);
//    }
//
//    void updateMark(int left, int right, int value) {
//        propagate();
//        markValue = value;
//    }
//
//    void propagate() {
//        if (markValue == -1)
//            return;
//        if (isLeaf()) {
//            c += markValue;
//            resetMark();
//            return;
//        }
//        c += (right - left + 1) * markValue;
//
//        l -> markValue += markValue;
//        r -> markValue += markValue;
//
//        resetMark();
//    }
//};
//
//}
