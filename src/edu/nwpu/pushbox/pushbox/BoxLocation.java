package edu.nwpu.pushbox.pushbox;

/**
 * 箱子位置类
 * 用栈实现位置，可实现返回上一步操作
 * @author laomaotao
 */
public class BoxLocation {
    //链表形式实现
    public static Location head;
    private int size;
    //构造方法
    public BoxLocation(){
        head =new Location();
        size=0;
    }
    //存储当前位置信息
    public void saveLocation(int x,int y){
        Location tmp =head;
        Location toBeAdded =new Location(x,y);
        if(tmp==null){  //头结点为空
            tmp=toBeAdded;
        }else{  //头结点不为空
            while(tmp.next!=null){
                tmp=tmp.next;
            }
            tmp.next=toBeAdded;
        }
        System.out.println("箱子坐标"+toBeAdded.x+" "+toBeAdded.y);
        size++;
    }
    //返回上一步位置信息
    public String returnLocation(){
        Location tmp =head;
        if(tmp==null){ //头结点为空
            return null;
        }else {  //头结点不为空
            if(tmp.next==null){ //头结点的下一个节点为空
                head =null;  //删除已经出栈的头节点
            }else{  //头结点的下一个节点不为空
                while (tmp.next.next != null) {  //遍历回到上一个节点
                    tmp = tmp.next;
            }
                tmp.next=null; //删除已经出栈的数据
            }
            size--;
            return tmp.x+"_"+tmp.y;
        }
    }
    //返回位置长度
    public int returnSize(){
        return size;
    }

    /**
     * 内部类，储存位置信息，及下一个节点信息
     */
    static class Location {
        int x;
        int y;
        Location next;

        //构造方法
        public Location(int x,int y){
            this.x=x;
            this.y=y;
            this.next =null;
        }

        public Location(){
            this.x=0;
            this.y=0;
            this.next=null;
        }
    }
}
