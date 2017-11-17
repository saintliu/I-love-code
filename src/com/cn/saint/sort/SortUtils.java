package com.cn.saint.sort;

/** 
* @author saint
* @version 创建时间：2017年8月27日 上午10:45:08 
* 类说明 
*/
public class SortUtils {
	
	/**
	 * 插入排序
	 *                            1
	 * 5，6，1，7，3  -->     5, 6, , 7, 3
	 * 
	 * 第一此比较6根1比，6填入1的位置
	 * 第二次比较5根1比，5填入原来6的位置
	 * 
	 * 默认第一个元素是有序的，从第二个元素开始排序,比较它前面的元素，直到有比它小的数就停下来
	 */
   public static int[] InsertSort(int[] arr) {
	   for(int i=1;i<arr.length;i++) {
		  int j = i-1;
		  //保存当前元素的值
		  int temp = arr[i];
		  //向前遍历：条件是j必须大于等于0，而且当前元素小于前面元素
		  while(j>=0 && arr[j]>temp) {
			  //后移
			  arr[j+1] = arr[j];
			  j--;
		  }
		  /**
		   * j = 1 时， arr[j] = 6, temp = 1  ; arr[j+1] = arr[j]    5, , 6, 7,3
		   * j = 0 时， arr[j] = 5, temp = 1  ; arr[j+1] = arr[j]    , 5,6,7,3
		   * 
		   * j--, j = -1;
		   */
		  
		  //把temp插入到j后面
		  arr[j+1] = temp;             // arr[0] = 1     1,5,6,7,3
	   }
	   return arr;
   }
   
   /**
    * 冒泡排序
    * 
    * 原理是相邻的2个元素相互比较，需要就互相交换位置，每一次都会把最大的数放在最后面，所以递归的时候就可以直接忽略最后一个元素
    */
   public static int[] BabelSort(int[] arr, int position) {
	 //如果数组只有一个元素，直接返回，无需排序
	 		if(arr.length == 1){
	 			return arr;
	 		}
	 		
	 		//如果数组大于一个元素，则需要进行比较
	 		for(int i=0;i<position-1;i++){
	 			//如果前面的数值大于其后面的数值，则进行交换
	 			if(arr[i]>arr[i+1]) {
	 				int temp; 
	 				temp = arr[i+1];
	 				arr[i+1] = arr[i];
	 				arr[i]= temp;
	 			}
	 			//每一趟都是把最大的数放在最后面，所以可以不用再扫描最后一个元素
	 			BabelSort(arr, position -1);
	 		}
	 	 return arr; 
     }
   
   /**
    * 快速排序
    * 
    * 关键是找基准点（最左边），然后左右两边按照大小排列，然后递归
    */
   public static void QuickSort(int[] arr, int left, int right) {
	   if(left >= right) return;
	   //设置最左边的数为基准点
	   int p = arr[left]; 
	   //记录最左边的坐标
	   int i = left;
	   //记录最右边的坐标
	   int j = right;
	   //5,6,1,7,3
	   while(i<j) {
		   //从最右边开始和第一个元素比较，直至找到比最左边大的元素
		   while(arr[j]>=p && i<j) {
			   j--;
		   }
		   //从最左边开始和第一个元素比较，直至找到比最左边小的元素
		   while(arr[i]<=p && i<j) {
			   i++;
		   }
		   if(i<j) {
			   int temp = arr[i]; 
			   arr[i] = arr[j];
			   arr[j] = temp;
		   }
		   
	   }
	   arr[left] = arr[i];
	   arr[i] = p;
	   
	   QuickSort(arr, left, i-1);
	   QuickSort(arr, i+1, right);
   }
}
