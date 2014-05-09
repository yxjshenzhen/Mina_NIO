package com.you.mina.handler;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.you.mina.bean.EquipmentBean;
import com.you.mina.datautil.JsonDataUtil;
import com.you.mina.dbutil.SQLUtil;

public class ReceivedJsonDataHandler extends IoHandlerAdapter {
	
	//阻塞队列防止线程冲突
	private BlockingQueue<EquipmentBean> queue = new LinkedBlockingQueue<EquipmentBean>();
	private SQLUtil sqlUtil = new SQLUtil();
	
	public ReceivedJsonDataHandler() {
		for (int i = 0; i < 50; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							//获取对象如果没有则等待
							EquipmentBean bean = queue.take();
		
							if (sqlUtil.addEquipmentBean(bean)) {
								//插入成功处理方案
							} else {
								//插入失败处理方案
							}
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();	
		}
	}
	
	@Override
	public void sessionCreated(IoSession session) {
		// 显示客户端的ip和端口
		System.out.println(session.getRemoteAddress().toString());
	}
	//没用放弃
	//ArrayList<EquipmentBean> arrayList = new ArrayList<EquipmentBean>();
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String str = message.toString();
		if (str.trim().equalsIgnoreCase("quit")) {
			// 结束会话
			session.close();
			return;
		}
		Date date = new Date();
		// 返回当前时间的字符串
		session.write("{'Successful': true,'Message': null}"/*+arrayList.size()*/);
		System.out.println("Message written..." + str);
		
		
		JsonDataUtil jsonDataUtil = new JsonDataUtil();
		//对象转换
		EquipmentBean  bean = jsonDataUtil.sensorData(str);
		//插入缓存队列
		if (queue.add(bean)) {
			/*
			 * 插入成功处理
			 * 一般不处理也行
			 */
		}else {
			/*
			 * 插入失败处理
			 * 最可能原因内存不够
			 */
		}
		//arrayList.add(jsonDataUtil.sensorData(str));
		session.write(bean.getType());
		
		//觉得没必要了
		/*if(arrayList.size()==500000){
			System.out.println("=========================================================================================");
			SQLUtil sqlUtil = new SQLUtil();
			sqlUtil.addEquipmentBeanList(arrayList);
			arrayList.clear();
		}*/
		
	}

}
