package personal.blockchain.domain.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import personal.blockchain.domain.dto.type.Parameter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Filter implements Parameter {
	
	private List<FilterTopic<?>> topics;
	
	public Filter()
	{
		topics = new ArrayList<>();
	}
	
	public Filter addSingleTopic(String topic)
	{
		topics.add(new SingleTopic(topic));
		return this;
	}
	
	public Filter setNullTopic()
	{
		topics = Collections.emptyList();
		return this;
	}
	
	public Filter addNullTopic()
	{
		topics.add(new SingleTopic());
		return this;
	}
	
	public Filter addOptionalTopics(String... optionalTopics)
	{
		topics.add(new ListTopic(optionalTopics));
		return this;
	}
	
	//public T addOptionalTopics
	
	public List<FilterTopic<?>> getTopics()
	{
		return topics;
	}
	
	public interface FilterTopic<T>
	{
		@JsonValue
		T getValue();
	}
	
	public static class SingleTopic implements FilterTopic<String>
	{
		private String topic;
		
		public SingleTopic()
		{
			this.topic = null;
		}
		public SingleTopic(String topic)
		{
			this.topic = topic;
		}
		
		@Override
		public String getValue() {
			return topic;
		}
		
	}
	
	public static class ListTopic implements FilterTopic<List<SingleTopic>>
	{

		private List<SingleTopic> topics;
		
		public ListTopic(String... optionalTopics)
		{
			topics = new ArrayList<>();
			for(String topic : optionalTopics)
			{
				if(topic != null) topics.add(new SingleTopic(topic));
				else topics.add(new SingleTopic());
			}
		}
		
		@Override
		public List<SingleTopic> getValue() {
			return topics;
		}
		
	}
}
