package solutions.stack;

/**
 * 比较含退格的字符串
 *
 * @author : xianzilei
 * @date : 2020/10/19 8:17
 */
public class Solution844 {

  /**
   * 解法一：栈
   *
   * @param S 1
   * @param T 2
   * @return boolean
   * @author xianzilei
   * @date 2020/10/19 8:30
   **/
  public boolean backspaceCompare(String S, String T) {
    //解题思路：消除#进行比较
    return getResult(S).equals(getResult(T));
  }

  //计算消除#后的字符串
  private String getResult(String str) {
    StringBuilder stringBuilder = new StringBuilder();
    //循环遍历字符串
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      //如果当前字符为#号
      if (ch == '#') {
        //如果栈不为空，则出栈
        if (stringBuilder.length() > 0) {
          stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
      }
      //非#字符直接入栈
      else {
        stringBuilder.append(ch);
      }
    }
    //返回结果
    return stringBuilder.toString();
  }

  /**
   * 解法二：双指针
   *
   * @param S 1
   * @param T 2
   * @return boolean
   * @author xianzilei
   * @date 2020/10/19 8:30
   **/
  public boolean backspaceCompare2(String S, String T) {
    //解题思路：从后往前遍历，遇到#则skip+1，遇到字母则将skip-1直到skip为0，直到找到第一个未被删除的字母进行比较

    //S字符串当前需要删除的字符个数
    int skipS = 0;
    //T字符串当前需要删除的字符个数
    int skipT = 0;
    //双指针（遍历的索引位置）
    int indexS = S.length() - 1;
    int indexT = T.length() - 1;
    while (indexS >= 0 || indexT >= 0) {
      //遍历找到S当前第一个未被删除的字母
      while (indexS >= 0) {
        if (S.charAt(indexS) == '#') {
          skipS++;
          indexS--;
        } else {
          if (skipS > 0) {
            skipS--;
            indexS--;
          } else {
            break;
          }
        }
      }
      //遍历找到T当前第一个未被删除的字母
      while (indexT >= 0) {
        if (T.charAt(indexT) == '#') {
          skipT++;
          indexT--;
        } else {
          if (skipT > 0) {
            skipT--;
            indexT--;
          } else {
            break;
          }
        }
      }
      //如果二者索引没有越界，比较字符
      if (indexS >= 0 && indexT >= 0) {
        if (S.charAt(indexS) != T.charAt(indexT)) {
          return false;
        }
      } else {
        //如果存在一个越界，一个没有越界，则直接返回false
        if (indexS >= 0 || indexT >= 0) {
          return false;
        }
      }
      indexS--;
      indexT--;
    }
    return true;
  }

  public static void main(String[] args) {
    Solution844 solution844 = new Solution844();
    System.out.println(solution844.backspaceCompare("ab#c", "ad#c"));
    System.out.println(solution844.backspaceCompare("ab##", "c#d#"));
    System.out.println(solution844.backspaceCompare("a##c", "#a#c"));
    System.out.println(solution844.backspaceCompare("a#c", "b"));
    System.out.println(solution844.backspaceCompare2("ab#c", "ad#c"));
    System.out.println(solution844.backspaceCompare2("ab##", "c#d#"));
    System.out.println(solution844.backspaceCompare2("a##c", "#a#c"));
    System.out.println(solution844.backspaceCompare2("a#c", "b"));
    System.out.println(solution844.backspaceCompare2("bbbextm", "bbb#extm"));
  }
}
