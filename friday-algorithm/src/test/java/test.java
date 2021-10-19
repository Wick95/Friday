
import com.surge.Friday.algorithm.datastructure.AbstractTreeClass;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.08.17
 */
public class test {
    public static void main(String[] args) {
//        PrintABC printABC = new PrintABC();
//        new Thread(printABC::printA).start();
//        new Thread(printABC::printB).start();
//        new Thread(printABC::printC).start();

        char[] preorder = new char[]{'F','B','A','D','C','E','G','H'};
        char[] inorder = new char[]{'A','B','C','D','E','F','G','H'};
        ResetTree.resetTree(preorder, inorder);
    }
}

class PrintABC {
    private final Lock lock = new ReentrantLock();
    private final Condition lockA = lock.newCondition();
    private final Condition lockB = lock.newCondition();
    private final Condition lockC = lock.newCondition();
    int flag = 0;/* 0->1,1->2,2->0**/

    public void printA() {
        int times = 0;
        lock.lock();
        try {
            while (times < 20) {
                while (flag != 0)
                    lockA.await();
                System.out.print("A");
                flag = 1;
                lockB.signal();
                times++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        int times = 0;
        lock.lock();
        try {
            while (times < 20) {
                while (flag != 1)
                    lockB.await();
                System.out.print("B");
                flag = 2;
                lockC.signal();
                times++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        int times = 0;
        lock.lock();
        try {
            while (times < 20) {
                while (flag != 2)
                    lockC.await();
                System.out.print("C");
                flag = 0;
                lockA.signal();
                times++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class ResetTree {
    public static TreeNode resetTree(char[] preorder, char[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private static TreeNode buildTreeHelper(char[] preOrder, int preStart, int preEnd, char[] inOrder, int inStart, int inEnd) {
        if (preStart == preEnd) {
            return null;
        }
        //找到根结点
        char root_val = preOrder[preStart];

        TreeNode root = new TreeNode(root_val);
        //在中序遍历中找到根
        int rootIndex = 0;
        for (int i = inStart; i < inEnd; i++) {
            if (root_val == inOrder[i]) {
                rootIndex = i;
                break;
            }
        }
        int leftNum = rootIndex - inStart;
        //递归的构造左子树
        root.left = buildTreeHelper(preOrder, preStart + 1, preStart + leftNum + 1, inOrder, inStart, rootIndex);
        //递归的构造右子树
        root.right = buildTreeHelper(preOrder, preStart + leftNum + 1, preEnd, inOrder, rootIndex + 1, inEnd);
        return root;
    }

    static class TreeNode extends AbstractTreeClass {
        public char val;
        public TreeNode left;
        public TreeNode right;

        TreeNode() {
        }

        public TreeNode(char val) {
            this.val = val;
        }

        TreeNode(char val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}