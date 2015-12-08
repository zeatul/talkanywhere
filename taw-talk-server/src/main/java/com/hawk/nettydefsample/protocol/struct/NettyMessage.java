package com.hawk.nettydefsample.protocol.struct;

public class NettyMessage {
	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	private Header header;

	private Object body;

	@Override
	public String toString() {
		return "NettyMessage [header=" + header + "]";
	}
}
