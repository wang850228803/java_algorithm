package com.example.test;

import java.awt.List;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Test implements Runnable{
    
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    private ListNode lNode;
    private Thread2 thread;
    
    public Test() {
        lNode = new ListNode(1);
        thread = new Thread2();
    }
    
    private void print1() {
        synchronized (lock1) {
            for (int i = 0; i < 50; i++) {
                System.out.println("print1:"+i);
            }
        }
    }
    
    private void print2() {
        synchronized (lock2) {
            for (int i = 0; i < 50; i++) {
                System.out.println("print2:"+i);
            }
        }
    }

    public void run() {
        // TODO Auto-generated method stub
        print1();
        print2();
    }
    
    public static boolean isIsomorphic(String s, String t) {
        HashMap<String, String> map = new HashMap<String, String>();
        HashSet<String> set = new HashSet<String>();
        StringBuffer temp = new StringBuffer();
        for(int i = 0; i < s.length(); i++) {
            set.add(t.charAt(i)+"");
            if (!map.containsKey(s.charAt(i)+"")) {
                map.put(s.charAt(i)+"", t.charAt(i)+"");
                temp.append(t.charAt(i));
            } else {
                temp.append(map.get(s.charAt(i)+""));
            }
        }
        if (temp.toString().equals(t) && map.size() == set.size())
            return true;
        else
            return false;
    }
    
    public static int countPrimes(int n) {
        if (n <= 3)
            return n > 2 ? 1 : 0;
        int primes[] = new int[n / 2];
        int count = 0;
        for (int i = 1; i < n / 2; i++) {
            primes[i] = 1;
        }
        for (int i = 1; i < n / 2; i++) {
            if (primes[i] == 1) {
                int step = (2 * i + 1) * 2;
                for (int j = (2 * i + 1) * 3; j < n; j +=  step)
                    primes[(j - 1) / 2] = 0;
            }
        }
        for (int i = 1; i < n / 2; i++) {
            if (primes[i] == 1)
                count ++;
        }
        return count + 1;
    }
    
     public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }
      
    public static ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;
        ListNode  temp = head;
        while(true) {
            if (temp.val == val && temp.next != null){
                temp.val = temp.next.val;
                temp.next = temp.next.next;
            }else if (temp.val == val && temp.next == null) {
                temp.val = 2;
                break;
            }else if(temp.next == null)
                break;
            else
                temp = temp.next;
        }
        return head;
    }
    
    //基本类型的参数传递  
    
    public static void testBasicType(int m) {  
  
        System.out.println("m=" + m);//m=50  
  
        m = 100;  
  
        System.out.println("m=" + m);//m=100  
  
    }  
  
     
  
    //参数为对象，不改变引用的值 ？？？？？？  
  
    public static void add(StringBuffer s) {  
  
        s.append("_add");  
  
    }  
  
     
  
    //参数为对象，改变引用的值 ？？？？？  
  
    public static void changeRef(StringBuffer s) {  
  
        s = new StringBuffer("Java");  
  
    }  
  
    public static int reverse(int x) {
        int result = 0;
        while(x != 0) {
            result *= 10;
            result += x % 10;
            x /= 10;
        }
        return result;
    } 
    
    public class Thread2 { 
        Object obj = new Object();
        public void m4t1() {  
             synchronized(obj) {  
                  int i = 5;  
                  while( i-- > 0) {  
                       System.out.println(Thread.currentThread().getName() + " : " + i);  
                       try {  
                            Thread.sleep(500);  
                       } catch (InterruptedException ie) {  
                       }  
                  }  
             }  
        }  
        public synchronized void m4t2() {  
             int i = 5;  
             while( i-- > 0) {  
                  System.out.println(Thread.currentThread().getName() + " : " + i);  
                  try {  
                       Thread.sleep(500);  
                  } catch (InterruptedException ie) {  
                  }  
             }  
        }  
    }
    
    public static boolean isPalindrome(int x) {
        int i;
        if (x < 0)
            return false;
        if (x >= 0 && x <= 9)
            return true;
        String string1 = (x % 10) + "";
        String string2 = (x + "").charAt(0) + "";
        if (string1==(string2))
            return false;
        else if ((x + "").length() == 2)
            return true;
        else
            return isPalindrome(Integer.parseInt((x + "").substring(1, (x + "").length() - 1)));
    }
    
    public static boolean isValidSudoku(char[][] board) {
        HashSet<Character> set1 = new HashSet<Character>();
        HashSet<Character> set2 = new HashSet<Character>();
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    count1++;
                    set1.add(board[i][j]);
                }
                if (board[j][i] != '.') {
                    count2++;
                    set2.add(board[j][i]);                    
                }
            }
            if (set1.size() != count1)
                return false;
            if (set2.size() != count2)
                return false;                
            set1.clear();
            count1 = 0;
            set2.clear();
            count2 = 0;
        }
        
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) 
                    for (int l = 0; l < 3; l++) {
                        if (board[i + k][j + l] != '.') {
                            count1++;
                            set1.add(board[i + k][j + l]);
                        }
                    }
                if (set1.size() != count1)
                    return false;
                set1.clear();
                count1 = 0;
            }
        return true;
    }
    
    public static int divide(int dividend, int divisor) {
        int MAX_INT = 2147483647;
        int flag = 1;
        long divisor1 = divisor;
        long dividend1 = dividend;
        if (divisor1 < 0) {
            divisor1 = -divisor1;
            flag = -flag;
        }
        if (dividend1 < 0) {
            dividend1 = -dividend1;
            flag = -flag;
        }
        if (divisor1 > dividend1)
            return 0;
        long res = 1;
        long sum = divisor1;
        while (true) {
            if (sum == dividend1 && res > MAX_INT)
                return flag == 1 ? MAX_INT : -MAX_INT;
            else if (sum == dividend1)
                return flag == 1 ? (int)res : (int)(-res);
            else if (sum < dividend1 && (sum << 1) > dividend1) {
                break;
            } else {
                res <<= 1;
                sum <<= 1;
            }
        }
        long sum1 = sum;
        long res1 = res;
        while(res1 != 1) {
            sum >>= 1;
            res1 >>=1;
            if (sum1 + sum == dividend1) {
                res += res1;
                break;
            }
            else if (sum1 + sum < dividend1) {
                sum1 += sum;
                res += res1;
            }
        }
        if (flag == 1)
            return res > MAX_INT ? MAX_INT : (int)res;
        else
            return res > MAX_INT ? -MAX_INT : -(int)res; 
    }
    
    public static ArrayList<Integer> findSubstring(String s, String[] words) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Set<String> set = new HashSet<String>();
        int wlen = words.length;
        for (int i = 0; i < wlen; i++)
            set.add(words[i]);
        int len = words[0].length();
        int slen = wlen * len;
        for (int j = 0; j < s.length() - slen + 1; j++) {
            String str = s.substring(j, j + slen);
            Set<String> set1 = new HashSet<String>();
            for (int k = 0; k < slen - len + 1; k = k + len)
                set1.add(str.substring(k,k+len));
            if (set1.size() != wlen)
                continue;
            Iterator iter = set.iterator();
            while(iter.hasNext()) {
                String s1 = (String)iter.next();
                set1.remove(s1);
            }
            if (set1.size() == 0)
                list.add(j);
        }
        return list;
    }
    
    public static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (true) {
            if (end - start < 2)
                break;
            int mid = start + (end - start) / 2;
            if (target == nums[mid])
                return mid;
            else if (target > nums[mid]) {
                if (nums[mid] > nums[end])
                    start = mid;
                else {
                    if (target == nums[end])
                        return end;
                    else if (target > nums[end])
                        end = mid;
                    else
                        start = mid;
                }
            }
            else {
                if (target == nums[start]) {
                    return start;
                } else if (target > nums[start]) {
                    end = mid;
                } else {
                    if (nums[mid] > nums[end])
                        start = mid;
                    else {
                        end = mid;
                    }
                }
            }
        }
        if (nums[start] == target)
            return start;
        else if (nums[end] == target)
            return end;
        else
            return -1;
    }
    
    public static int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        int start = 0;
        int end = nums.length - 1;
        int mid = (end - start)/2;
        while (end - start > 1) {
            mid = (end - start)/2;
            if (nums[mid] == target) {
                start = end = mid;
                while (start >= 0 && nums[start] == target) 
                    start--;
                while (end <= nums.length - 1 && nums[end] == target)
                    end++;
                res[0] = start + 1;
                res[1] = end - 1;
                return res;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == target && nums[end] == target) {
            res[0] = start;
            res[1] = end;
            return res;
        } else if (nums[start] == target) {
            res[0] = res[1] = start;
            return res;
        }
        else if (nums[end] == target) {
            res[0] = res[1] = end;
            return res;
        }
        else {
            res[0] = res[1] = -1;
            return res;
        }
    }
 
    public static String multiply(String num1, String num2) {
        int len = num2.length();
        int n;
        String s;
        int q = 1;
        String res = "";
        for (int i = 0; i < len; i++) {
            n = num2.charAt(len - 1 - i) - '0';
            s = subMult(n, num1, i);
            res = plus(res, s);
        }
        return res;
    }
    
    public static String subMult(int n, String num1, int i) {
        if (n == 0 || num1 == "0")
            return "0";
        StringBuffer res = new StringBuffer("");
        int l = num1.length();
        int j = 0;
        int n1;
        for (int k = 0; k < l; k++) {
            n1 = num1.charAt(l - 1 - k) - '0';
            n1 *= n;
            n1 += j;
            j = n1 / 10;
            res.insert(0, (char)(n1 % 10 + '0'));
        }
        if (j != 0) {
            res.insert(0, (char)(j + '0'));
        }
        while (i > 0) {
            res.append('0');
            i--;
        }
        return res.toString();
    }
    
    public static String plus(String res, String s) {
        int b = 0;
        StringBuffer r = new StringBuffer();
        String s1;
        int n;
        int l,l1;
        if (res.length() < s.length()) {
            s1 = s;
            l = res.length();
            l1 = s.length();
        } else {
            s1 = res;
            l = s.length();
            l1 = res.length();
        }
        for (int i = 0; i < l; i++) {
            n = res.charAt(res.length() - 1 - i) - '0' + s.charAt(s.length() - 1 - i) - '0' + b;
            b = n / 10;
            r.insert(0, (char)(n % 10 + '0'));
        }
        for (int j = 0; j < l1 - l; j++) {
            if (b == 1) {
                n = s1.charAt(l1 - l - 1 - j) - '0' + 1;
                b = n / 10;
                r.insert(0, (char)(n % 10 + '0'));
            } else {
                r.insert(0, s1.charAt(l1 - l - 1 - j));
            }
        }
        if (b == 1) {
            r.insert(0, '1');
        }
        return r.toString();
    }
    
    public static int firstMissingPositive(int[] nums) {
        int len = nums.length;
        int temp;
        int temp1;
        int res;
        int i;
        for (i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                temp = nums[i];
                nums[i] = 0;
                while (temp >= 1 && temp <= len) {
                    if (nums[temp - 1] < 1 || nums[temp - 1] > len) {
                        nums[temp - 1] = temp;
                        break;
                    } else {
                        temp1 = temp;
                        temp = nums[temp1 - 1];
                        nums[temp1 - 1] = temp1;
                    }
                }
            }
        }
        for (i = 0; i < len; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }
        return i + 1;
    }
    
    public static final int[] a = new int[] {1,2,3};
    public static final ArrayList<Integer> bArrayList = new ArrayList<Integer>();
    public static final int n = 10;
    
    public double myPow(double x, int n) {
        double temp;
        if (n == 0)
            return 1;
        else if (n == 1)
            return x;
        else  {
            temp = myPow(n/2,1);
            if (n % 2 == 0) {
                return temp * temp;
            } else {
                return temp * temp * x;
            }
        }
    }
    
    public static ArrayList<ArrayList<String>> solveNQueens(int n) {
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        ArrayList<String> subList = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        Set<Integer> set = new HashSet<Integer>();
        set.add(0);
        for (int i = 1; i < n; i++) {
            sb.append('.');
            set.add(i);
        }
        handler(list, subList, n, sb, 0, set, null);
        return list;
    }
    
    public static void handler(ArrayList<ArrayList<String>> list, ArrayList<String> subList, int n, StringBuffer sb, int i, Set<Integer> set1, Integer rm) {
            if (i == n) {
                ArrayList<String> subList1 = new ArrayList<String>();
                subList1.addAll(subList);
                list.add(subList1);
                return;
            }
            Set<Integer> set = new HashSet<Integer>(set1);
            if (rm != null)
                set.remove(rm);
            for (int item : set) {
                StringBuffer sb1 = new StringBuffer(sb);
                sb1.insert(item, 'Q');
                subList.add(sb1.toString());
                handler(list, subList, n, sb, ++i, set, item);
                subList.remove(i - 1);
                i--;
            }
            if (rm != null)
                set.add(rm);
    }
    
    public static ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int nr = matrix.length;
        int nc = matrix[0].length;
        int i = 0;
        for (;i < nr / 2; i++) {
            for (int j = i; j < nc - i; j++)
                list.add(matrix[i][j]);
            for (int k = i + 1; k < nr - i; k++)
                list.add(matrix[k][nc - 1 - i]);
            for (int l = nc - 2 - i; l >= i; l--)
                list.add(matrix[nr - 1 - i][l]);
            for (int m = nr - 2 - i; m > i; m--)
                list.add(matrix[m][i]);
        }
        if (nc % 2 == 0 && nr % 2 == 0)
            return list;
        else if (nc % 2 == 0) {
            for (int n = i; n < nc  - i; n++)
                list.add(matrix[i][n]);
            return list;
        } else if (nr % 2 == 0) {
            for (int o = i; o < nr  - i; o++)
                list.add(matrix[o][i]);
            return list;
        } else {
            list.add(matrix[i][i]);
            return list;
        }
    }
    
    public static class Interval {
             int start;
             int end;
             Interval() { start = 0; end = 0; }
             Interval(int s, int e) { start = s; end = e; }
         }
    
    public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> list = new ArrayList<Interval>();
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval inter1, Interval inter2) {
                return Integer.compare(inter1.start,inter2.start);
            }
        });
        int i = 1;
        Interval temp;
        for(; i < intervals.size(); i++) {
            if (intervals.get(i).start > intervals.get(i - 1).end) {
                list.add(intervals.get(i - 1));
            } else if (intervals.get(i).end < intervals.get(i - 1).end) {
                intervals.remove(i);
                intervals.add(i,intervals.get(i - 1));
            } else {
                temp = new Interval(intervals.get(i - 1).start, intervals.get(i).end);
                intervals.remove(i);
                intervals.add(i, temp);
            }
        }
        list.add(intervals.get(i - 1));
        return list;
    }
    
    public static String addBinary(String a, String b) {
        int alen = a.length();
        int blen = b.length();
        int min;
        int max;
        int i;
        StringBuffer l;
        int carry = 0;
        int cur;
        if (alen > blen) {
            l = new StringBuffer(a);
            min = blen;
        } else {
            l = new StringBuffer(b);
            min = alen;
        }
        for (i = 0; i < min; i++) {
            cur = carry + a.charAt(alen - 1 - i) - '0' + b.charAt(blen - 1 - i) - '0';
            carry = cur / 2;
            l.setCharAt(l.length() - 1 - i, (char)('0' + cur % 2));
        }
        if (carry != 0) {
            for (int j = 0; j < l.length() - min; j++) {
                if (carry == 0)
                    break;
                cur = carry + l.charAt(l.length() - 1 - i - j) - '0';
                carry /= 2;
                l.setCharAt(l.length() - 1 - i - j, (char)('0' + cur % 2));
            }
        }
        if (carry != 0)
            l.insert(0, 1);
        return l.toString();
    }
    
    public static String simplifyPath(String path) {
        StringBuffer sb = new StringBuffer("");
        Stack stack = new Stack();
        String[] ss = path.split("/");
        for (int i  = 0; i < ss.length; i++) {
            if (ss[i].equals("..")) {
                if (!stack.empty())
                    stack.pop();
            } else if (ss[i].equals(".")) {
                continue;
            } else if (!(ss[i].equals(""))){
                stack.push(ss[i]);
            }
        }
        while (!stack.empty()) {
            sb.insert(0,(String)stack.pop());
            sb.insert(0,"/");
        }
        if (sb.length()==0)
            return "/";
        else
            return sb.toString();
    }
    
    public static void sortColors(int[] nums) {
        int l = nums.length;
        int i = 0;
        int j = l - 1;
        int temp;
        while (i < l && nums[i] == 0) i++;
        while (j >= 0 && nums[j] == 2) j--;
        int k = i; // the first number which is not 0;
        for (; i <= j; i++) {
            if (nums[i] == 2) {
                temp = nums[j];
                nums[j] = 2;
                nums[i] = temp;
                j--;
                while (j >= 0 && nums[j] == 2) j--;
                i--;
            } else if (nums[i] == 0) {
                if (i != k) {
                    temp = nums[k];
                    nums[k] = 0;
                    nums[i] = temp;
                }
                k++;
            }
        }
        System.out.print("");
    }
    
    //反射
    private InnerClass iClass = new InnerClass();
    private static int ab = 0;
    
    private class InnerClass {
        public void print(int i) {
            System.out.print(ab + i);
        }
    }
    
    public static void main(String[] args) {
        Test test = new Test();
        try {
            Class c = Class.forName("com.example.test.Test$InnerClass");
            Class oClass = Class.forName("com.example.test.Test");
            Field field = oClass.getDeclaredField("ab");
            field.setAccessible(true);
            field.set(ab, 1);
            
            Field clsField = oClass.getDeclaredField("iClass");
            Object ic = clsField.get(test);
            
            Method method = c.getDeclaredMethod("print",int.class);
            method.invoke(ic, 2);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*int[] a = {0,1,0,2,1,0};
        sortColors(a);*/
        //simplifyPath("//...//");
        /*int num = 50000;
        long n = (long)num * num;*/
        //addBinary("111","1");
        
        /*ArrayList<Interval> arrayList = new ArrayList<Interval>();
        arrayList.add(new Interval(1, 6));
        arrayList.add(new Interval(4, 6));
        merge(arrayList);*/
        
        
        /*int[][] m = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
        spiralOrder(m);*/
        //solveNQueens(8);
        /*a[0] = 4;
        System.out.print(a[0]);
        bArrayList.add(1);
        n = 12;*/
        /*int[] a = {1,1};
        firstMissingPositive(a);*/
        //multiply("0", "52");
        /*int[] nums = {5,7,7,8,8,10};
        searchRange(nums, 8);*/
        /*int[] nums = {4,5,6,7,8,1,2,3};
        search(nums, 8);*/
        /*String[] strings = {"foo","bar"};
        findSubstring("barfoothefoobarman", strings);*/
        //divide(-2147483648,1);
        /*char a[][] = {{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','3','.','.'},{'.','.','.','1','8','.','.','.','.'},
                {'.','.','.','7','.','.','.','.','.'},{'.','.','.','.','1','.','9','7','.'},{'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','3','6','.','1','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','2','.'}};
        isValidSudoku(a);
        ArrayList<Integer> map = new ArrayList<Integer>() {{
            add(1);
            add( 3);
        }};
        System.out.print(map.size());*/
        /*String s[] = {"abc", "def", "ghi", "jkl", "mno", "pqrs",
                "tuv", "wxyz"};
        String digits = "123";
        String string=s[Integer.parseInt(digits.charAt(digits.length() - 1)) - 2];
        System.out.print(string);*/
        //isPalindrome(11);
        // TODO Auto-generated method stub
        /*Test mThread = new Test();
        new Thread(mThread, "a").start();
        new Thread(mThread, "b").start();*/
        //isIsomorphic("", "");
        //countPrimes(4);
        //removeElements(new Test().lNode,1);
        /*int i = 50;  
        
        testBasicType(i);  
  
        System.out.println(i);//i=50  
  
        StringBuffer sMain = new StringBuffer("init");  
  
        System.out.println("sMain=" + sMain.toString());//sMain=init  
  
        add(sMain); 
        System.out.println("sMain=" + sMain.toString());//sMain=init_add  
        
        changeRef(sMain);  
  
        System.out.println("sMain=" + sMain.toString());//sMain=init_add
*/
       // System.out.println('a' - 'A');
        /*final Thread2 myt2 = new Test().thread;  
        Thread t1 = new Thread(  new Runnable() {  public void run() {  myt2.m4t1();  }  }, "t1"  );  
        Thread t2 = new Thread(  new Runnable() {  public void run() { myt2.m4t2();   }  }, "t2"  );  
        t1.start();  
        t2.start(); */ 
    }
}
