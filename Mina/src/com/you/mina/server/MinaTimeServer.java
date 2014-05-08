package com.you.mina.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.Map;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.you.mina.handler.ReceivedJsonDataHandler;
import com.you.mina.handler.TimeServerHandler;

public class MinaTimeServer {

	private static final int	PORT	= 9123;	// 定义监听端口
	
	private static IoAcceptor acceptor;
	
	public static void main(String[] args) throws IOException {
		startMinaServer();
		
		System.out.println(getConNum());
		
		sendConMessage();
		
	}
	
	public static void startMinaServer(){
		
		acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		//acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));// 指定编码过滤器
		acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory()));//支持中文
		acceptor.setHandler(new ReceivedJsonDataHandler());// 指定业务逻辑处理器
		acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));// 设置端口号
		//下面两行设置摘自官网样例
		acceptor.getSessionConfig().setReadBufferSize( 2048 );
	    acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 10 );
		try {
			acceptor.bind();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 启动监听
	}
	
	/**
	 * 获得客户端连接总数
	 * @return
	 */
	public static int getConNum(){
		
		int num = acceptor.getManagedSessionCount();
		System.out.println("num:" + num);
		
		return num;
	}
	
	/**
	 * 向每个客户端发送消息
	 * @return
	 */
	public static void sendConMessage(){
		
		IoSession session;
		
		Map conMap = acceptor.getManagedSessions();
		
		Iterator iter = conMap.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			session = (IoSession)conMap.get(key);
			session.write("" + key.toString());
		}
	}
}