package com.example.mynavigation.utils;

import android.graphics.PointF;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class PictureTouch implements View.OnTouchListener {

    //缩放控制
    private Matrix matrix = new Matrix();
    private Matrix savematrix = new Matrix();

    //不同的状态显示
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;

    //定义第一个按下的点，两个接触点的重点，以及初始的两指按下的距离
    private PointF startPoint = new PointF();
    private PointF midPoint = new PointF();
    private float oriDis = 1f;

    //计算两点之间的距离
    private float distance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return Float.valueOf(String.valueOf(Math.sqrt(x * x + y * y)));
    }

    //计算两个触摸点的中点
    private PointF middle(MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        return new PointF(x / 2, y / 2);
    }

    @Override
    //参数1:用户触摸的控件；参数2:用户触摸所产生的事件
    public boolean onTouch(View v, MotionEvent event) {
        ImageView imageView = (ImageView) v;
        //判断事件的类型
        //得到低八位才能获取动作，所以要屏蔽高八位(通过与运算&255)
        //ACTION_MASK就是一个常量，代表255
        switch (event.getAction()&MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN://手指下压
                mode=DRAG;
                savematrix.set(imageView.getImageMatrix());//记录ImageView当前的移动位置
                startPoint.set(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE://手指在屏幕移动，改事件会不断被调用
                if(mode==DRAG){//拖拉模式
                    float dx=event.getX()-startPoint.x;//得到在x轴的移动距离
                    float dy=event.getY()-startPoint.y;//得到在y轴的移动距离
                    matrix.set(savematrix);//在没有进行移动之前的位置基础上进行移动
                    //实现位置的移动
                    matrix.postTranslate(dx, dy);
                }else if(mode==ZOOM){//缩放模式
                    float endDis=distance(event);//结束距离
                    if(endDis>10f){//防止不规则手指触碰
                        //结束距离除以开始距离得到缩放倍数
                        float scale=endDis/oriDis;
                        //通过矩阵实现缩放
                        //参数：1.2.指定在xy轴的放大倍数;3,4以哪个参考点进行缩放
                        //开始的参考点以两个触摸点的中心为准
                        matrix.set(savematrix);//在没有进行缩放之前的基础上进行缩放
                        matrix.postScale(scale,scale,midPoint.x,midPoint.y);
                    }

                }

                break;
            case MotionEvent.ACTION_UP://手指离开屏幕
            case MotionEvent.ACTION_POINTER_UP://当屏幕上已经有手指离开屏幕，屏幕上还有一个手指，就会触发这个事件
                mode=NONE;
                break;
            case MotionEvent.ACTION_POINTER_DOWN://当屏幕上已经有触点(手指)，再有一个手指按下屏幕，就会触发这个事件
                mode=ZOOM;
                oriDis=distance(event);
                if(oriDis>10f){//防止不规则手指触碰
                    midPoint=middle(event);
                    savematrix.set(imageView.getImageMatrix());//记录ImageView当前的缩放倍数
                }
                break;

            default:
                break;
        }
        //将imageView的矩阵位置改变
        imageView.setImageMatrix(matrix);
        return true;
    }


}
