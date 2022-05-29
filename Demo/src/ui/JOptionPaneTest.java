package ui;

import javax.swing.*;

public class JOptionPaneTest {
    public static void main(String[] args) {
        showConfirmDialog();
    }

    private static void showConfirmDialog() {
        int returnVal = JOptionPane.showConfirmDialog(null, "已经退出，是否继续");
        // System.out.println(returnVal);
        // System.out.println("JOptionPane.OK_OPTION = " + JOptionPane.OK_OPTION);
        // if (returnVal == JOptionPane.OK_OPTION) {
        //     System.out.println("选择确认");
        // }
        switch (returnVal) {
            case JOptionPane.OK_OPTION:
                System.out.println("选择 确认(Y Yes)");
                break;
            case JOptionPane.CANCEL_OPTION:
                System.out.println("选择取消");
                break;
            case JOptionPane.NO_OPTION:
                System.out.println("选择(N No)");
                break;
        }
    }
}
