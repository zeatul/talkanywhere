package org.jivesoftware.util;

import org.xmpp.packet.JID;

public class JIDUtils {
	
	public static JID asBareJID(JID jid){
		return new JID(jid.getNode(), jid.getDomain(), null, true);
	}

}
