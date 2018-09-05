package com.api.open.mina;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * Created by 003 on 2018/9/5.
 */
public class MinaClient {

    public static void main(String[] args) {

        /**创建一个mina客户端Socket连接器*/
        NioSocketConnector nioSocketConnector = new NioSocketConnector();

        /**获取过滤链*/
        DefaultIoFilterChainBuilder filterChain = nioSocketConnector.getFilterChain();

        /**添加一个行读取过滤器*/
        filterChain.addLast("defaultMinaFilter", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));

        /**添加一个消息处理器*/
        nioSocketConnector.setHandler(new DefaultClientMinaHandler());

        /**设置一个连接超时时间*/
        nioSocketConnector.setConnectTimeoutMillis(10000);

        /**连接一个服务端*/
        ConnectFuture connectFuture = nioSocketConnector.connect(new InetSocketAddress("47.100.122.205", 9999));

        /**等待连接成功*/
        connectFuture.awaitUninterruptibly();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请输入：");
            String msg = scanner.nextLine();
            connectFuture.getSession().write(msg);
        }
    }
}
