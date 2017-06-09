package test;

public class Helper {

	public static String resolveNodeNameFromConsumerId(String consumerId, String groupName) {
		if (consumerId == null) {
			return null;
		}
		if (consumerId.equals(groupName)) {
			return consumerId;
		}
		if (consumerId.length() < 23) {
			return consumerId;
		}
		String nodeWithGroup = consumerId.substring(0, consumerId.length() - 23);
		return nodeWithGroup.substring(groupName.length() + 1, nodeWithGroup.length());
	}

	public static String resolveNodeNameFromThreadId(String threadId, String groupName) {
		return resolveNodeNameFromConsumerId(resolveConsumerId(threadId), groupName);
	}

	public static String resolveConsumerId(String threadId) {
		return threadId.substring(0, threadId.lastIndexOf('-'));
	}
	
	
}
