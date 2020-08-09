/**
 * 
 */
package br.com.desafio.util;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

import br.com.desafio.controller.dto.MarcaDto;
import br.com.desafio.controller.dto.PatrimonioDto;
import br.com.desafio.model.Marca;
import br.com.desafio.model.Patrimonio;

/**
 * @author valbercarreiro
 *
 */
public class PatrimonioConvertMap {

	public final Persist persist = new Persist();
	
	public class Persist extends PropertyMap<PatrimonioDto, Patrimonio> {

		@Override
		protected void configure() {
			
			this.map().setId(this.source.getId());
			this.map().setNome(this.source.getNome());
			this.map().setDescricao(this.source.getDescricao());
			this.map().setNumeroTombamento(this.source.getNumeroTombamento());
			using(marcaConverter).map(this.source.getMarca(), this.map().getMarca());
			
		}
		
		Converter<MarcaDto, Marca> marcaConverter = new AbstractConverter<MarcaDto, Marca>() {

			@Override
			protected Marca convert(MarcaDto source) {
				return new Marca(source.getId(), source.getNome());
			}

			
		};
		
	}
	
}
