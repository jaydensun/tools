import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.*;

public class TreeDemo {
    public static void main(String[] args) {

        // 创建没有父节点和子节点、但允许有子节点的树节点，并使用指定的用户对象对它进行初始化。
        // public DefaultMutableTreeNode(Object userObject)
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("软件部");
        node1.add(new DefaultMutableTreeNode(new User("小花")));
        node1.add(new DefaultMutableTreeNode(new User("小虎")));
        node1.add(new DefaultMutableTreeNode(new User("小龙")));

        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("销售部");
        node2.add(new DefaultMutableTreeNode(new User("小叶")));
        node2.add(new DefaultMutableTreeNode(new User("小雯")));
        node2.add(new DefaultMutableTreeNode(new User("小夏")));

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("职员管理");

        top.add(new DefaultMutableTreeNode(new User("总经理")));
        top.add(node1);
        top.add(node2);
        top.add(new DefaultMutableTreeNode());
//        final JTree tree = new JTree(top);
        TreeNode root = new MyTreeNode("root", null);
        final JTree tree = new JTree(root);
        JFrame f = new JFrame("JTreeDemo");
        f.add(tree);
        f.setSize(300, 300);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 添加选择事件
        tree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
                        .getLastSelectedPathComponent();

                if (node == null)
                    return;

                Object object = node.getUserObject();
                if (node.isLeaf()) {
                    User user = (User) object;
                    System.out.println("你选择了：" + user.toString());
                }

            }
        });
    }

    private static class MyTreeNode implements TreeNode {
        private String name;
        private MyTreeNode parent;
        private List<String> list = Arrays.asList("b", "a", "d", "c");

        public MyTreeNode(String name, MyTreeNode parent) {
            this.name = name;
            this.parent = parent;
        }

        @Override
        public TreeNode getChildAt(int childIndex) {
            return new MyTreeNode(list.get(childIndex), this);
        }

        @Override
        public int getChildCount() {
            return list.size();
        }

        @Override
        public TreeNode getParent() {
            return parent;
        }

        @Override
        public int getIndex(TreeNode node) {
            return 0;
        }

        @Override
        public boolean getAllowsChildren() {
            return true;
        }

        @Override
        public boolean isLeaf() {
            return false;
        }

        @Override
        public Enumeration children() {
            List<TreeNode> returnChildren = new ArrayList<>();
            Collections.sort(list);
            for (String s : list) {
                returnChildren.add(new MyTreeNode(s, this));
            }
            System.out.println(returnChildren);
            return Collections.enumeration(returnChildren);
        }

        @Override
        public String toString() {
            return name;
        }
    }
}

class User {
    private String name;

    public User(String n) {
        name = n;
    }

    // 重点在toString，节点的显示文本就是toString
    public String toString() {
        return name;
    }
}