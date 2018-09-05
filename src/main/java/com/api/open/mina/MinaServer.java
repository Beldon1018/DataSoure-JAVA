package com.api.open.mina;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by 003 on 2018/9/5.
 */
public class MinaServer {

    public static void startServer() {

        /**创建一个非阻塞的Server端Socket*/
        SocketAcceptor acceptor = new NioSocketAcceptor();

        /**获取这个*/
        DefaultIoFilterChainBuilder filterChain = acceptor.getFilterChain();

        /**创建一个过滤器，一行一行的读取，（\r\n）*/
        filterChain.addLast("default_Mina_Filter", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));

        /**添加一个自定义个服务端处理器*/
        acceptor.setHandler(new DefaultServerMinaHandler());

        /**绑定一个端口*/
        int port = 9999;
        try {
            acceptor.bind(new InetSocketAddress(port));

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("服务启动完成，监听端口:" + port);
    }
}
