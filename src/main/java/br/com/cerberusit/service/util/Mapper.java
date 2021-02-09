package br.com.cerberusit.service.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class Mapper {
	
	
	private static final ModelMapper modelMapper = new ModelMapper();

	public static <D,T> Page<D> mapEntidadeParaDtoPage(Page<T> entidade, Class<D> dto){
		log.info("CHAMANDO MAPPER");
		return entidade
				.map(obj -> modelMapper.map(obj, dto));
	}
	
	public static <S, T> List<T> mapEntidadeParaDtoList(List<S> origem, Class<T> targetClass) {
		log.info("CHAMANDO MAPPER");
	    return origem
	      .stream()
	      .map(obj -> modelMapper.map(obj, targetClass))
	      .collect(Collectors.toList());
	}
}
