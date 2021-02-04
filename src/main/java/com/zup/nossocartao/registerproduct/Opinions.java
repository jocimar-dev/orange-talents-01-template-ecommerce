package com.zup.nossocartao.registerproduct;

import com.zup.nossocartao.opinion.Opinion;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Isola as operaoces sobre um conjunto de opinioes
 * @author albertoluizsouza
 *
 */
public class Opinions {

	private Set<Opinion> opinions;

	public Opinions(Set<Opinion> opinions) {
		this.opinions = opinions;
	}

	public <T> Set<T> mapOpinions(Function<Opinion, T> mapFunction) {
		return this.opinions.stream().map(mapFunction)
				.collect(Collectors.toSet());
	}

	public double media() {
		Set<Integer> notes = mapOpinions(Opinion::getNote);
		OptionalDouble possibleMedia = notes.stream().mapToInt(nota -> nota).average();
		return possibleMedia.orElse(0.0);
	}


	public int total() {
		return this.opinions.size();
	}
	
	

}
