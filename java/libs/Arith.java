

import java.math.BigDecimal;

/**
* 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精
* 确的浮点数运算，包括加减乘除和四舍五入。
*/
public class Arith
{//默认除法运算精度
   private static final int DEF_DIV_SCALE = 10;

   //这个类不能实例化
   private Arith()
   {
   }

   /**
    * 提供精确的加法运算。
    * @param v1 被加数
    * @param v2 加数
    * @return 两个参数的和
    */
   public static double add(double v1, double v2)
   {
      BigDecimal b1 = new BigDecimal(Double.toString(v1));
      BigDecimal b2 = new BigDecimal(Double.toString(v2));
      double result = b1.add(b2).doubleValue();
      result = div(result, 1.0, 2);//两位小数的处理
      return result;
   }

   /**
    * 提供精确的加法运算。
    * @param v1 被加数
    * @param v2 加数
    * @return 两个参数的和
    */
   public static double add(String v1, String v2)
   {
      if (CmUtil.isNull(v1)) v1 = "0";
      if (CmUtil.isNull(v2)) v2 = "0";
      BigDecimal b1 = new BigDecimal(v1);
      BigDecimal b2 = new BigDecimal(v2);
      double result = b1.add(b2).doubleValue();
      result = div(result, 1.0, 2);//两位小数的处理
      return result;
   }

   /**
    * 提供精确的减法运算。
    * @param v1 被减数
    * @param v2 减数
    * @return 两个参数的差
    */
   public static double sub(double v1, double v2)
   {
      BigDecimal b1 = new BigDecimal(Double.toString(v1));
      BigDecimal b2 = new BigDecimal(Double.toString(v2));
      double result = b1.subtract(b2).doubleValue();
      result = div(result, 1.0, 2);//两位小数的处理
      return result;
   }

   /**
    * 提供精确的减法运算。
    * @param v1 被减数
    * @param v2 减数
    * @return 两个参数的差
    */
   public static double sub(String v1, String v2)
   {
      if (CmUtil.isNull(v1)) v1 = "0";
      if (CmUtil.isNull(v2)) v2 = "0";
      BigDecimal b1 = new BigDecimal(v1);
      BigDecimal b2 = new BigDecimal(v2);
      double result = b1.subtract(b2).doubleValue();
      result = div(result, 1.0, 2);//两位小数的处理
      return result;
   }

   /**
    * 提供精确的乘法运算。
    * @param v1 被乘数
    * @param v2 乘数
    * @return 两个参数的积
    */
   public static double mul(double v1, double v2)
   {
      //      BigDecimal b1 = new BigDecimal(Double.toString(v1));
      //      BigDecimal b2 = new BigDecimal(Double.toString(v2));
      //      return b1.multiply(b2).doubleValue();
      return mul(Double.toString(v1), Double.toString(v2));
   }

   /**
    * 提供精确的乘法运算。
    * @param v1 被乘数
    * @param v2 乘数
    * @return 两个参数的积
    */
   public static double mul(String v1, String v2)
   {
      if (CmUtil.isNull(v1)) v1 = "0";
      if (CmUtil.isNull(v2)) v2 = "0";
      BigDecimal b1 = new BigDecimal(v1);
      BigDecimal b2 = new BigDecimal(v2);
      double result = b1.multiply(b2).doubleValue();
      result = div(result, 1.0, 2);//两位小数的处理
      return result;
   }

   /**
    * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
    * 小数点以后10位，以后的数字四舍五入。
    * @param v1 被除数
    * @param v2 除数
    * @return 两个参数的商
    */
   public static double div(double v1, double v2)
   {
      return div(v1, v2, DEF_DIV_SCALE);
   }

   /**
    * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
    * 定精度，以后的数字四舍五入。
    * @param v1 被除数
    * @param v2 除数
    * @param scale 表示表示需要精确到小数点以后几位。
    * @return 两个参数的商
    */
   public static double div(double v1, double v2, int scale)
   {
      if (scale < 0)
      {
         throw new IllegalArgumentException(
            "The scale must be a positive integer or zero");
      }
      BigDecimal b1 = new BigDecimal(Double.toString(v1));
      BigDecimal b2 = new BigDecimal(Double.toString(v2));
      return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
   }

   /**
    * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
    * 定精度，以后的数字四舍五入。
    * @param v1 被除数
    * @param v2 除数
    * @param scale 表示表示需要精确到小数点以后几位。
    * @return 两个参数的商
    */
   public static double div(String v1, String v2, int scale)
   {
      if (scale < 0)
      {
         throw new IllegalArgumentException(
            "The scale must be a positive integer or zero");
      }
      if (CmUtil.isNull(v1)) v1 = "0";
      if (CmUtil.isNull(v2)) v2 = "1";
      BigDecimal b1 = new BigDecimal(v1);
      BigDecimal b2 = new BigDecimal(v2);
      return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
   }

   /**
    * 提供精确的小数位四舍五入处理。
    * @param v 需要四舍五入的数字
    * @param scale 小数点后保留几位
    * @return 四舍五入后的结果
    */
   public static double round(double v, int scale)
   {

      if (scale < 0)
      {
         throw new IllegalArgumentException(
            "The scale must be a positive integer or zero");
      }
      BigDecimal b = new BigDecimal(Double.toString(v));
      BigDecimal one = new BigDecimal("1");
      return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
   }

   public static void main(String[] arg)
   {
      String a = "01";
      String b = Arith.div(a, "100", 2) + "";
      System.out.println(b);

      String x = "19.80", y = "0.07";
      String z = Arith.mul(x, y) + "";
      System.out.println(z);
      
      String e = "1.010";
      String r = Arith.mul(e, "100") + "";
      r = r.substring(0, r.indexOf("."));
      System.out.println(r);
   }
}
