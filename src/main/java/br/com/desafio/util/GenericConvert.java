/**
 * 
 */
package br.com.desafio.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

/**
 * @author valbercarreiro
 *
 */
public class GenericConvert {

	public static <T, E> E convertWhitMapping(T source, Class<E> typeDestination, PropertyMap<T, E> mapping) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.addMappings(mapping);
		
		return modelMapper.map(source, typeDestination);
	}
}
