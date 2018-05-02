package com.xxx.proxy.custom;
import java.lang.reflect.Method;
public class $Proxy0 implements com.xxx.design.custom.Dao
{
private MyInvocationHandler h;
public $Proxy0(MyInvocationHandler h) { this.h = h; }
public void query (){
try {
Method m = com.xxx.design.custom.Dao.class.getMethod("query",new Class[]{});
this.h.invoke(this,m,null);} catch (Throwable e) { e.printStackTrace();}
}public void modify (){
try {
Method m = com.xxx.design.custom.Dao.class.getMethod("modify",new Class[]{});
this.h.invoke(this,m,null);} catch (Throwable e) { e.printStackTrace();}
}}
