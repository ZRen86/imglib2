/*
 * #%L
 * ImgLib2: a general-purpose, multidimensional image processing library.
 * %%
 * Copyright (C) 2009 - 2016 Tobias Pietzsch, Stephan Preibisch, Stephan Saalfeld,
 * John Bogovic, Albert Cardona, Barry DeZonia, Christian Dietz, Jan Funke,
 * Aivar Grislis, Jonathan Hale, Grant Harris, Stefan Helfrich, Mark Hiner,
 * Martin Horn, Steffen Jaensch, Lee Kamentsky, Larry Lindsey, Melissa Linkert,
 * Mark Longair, Brian Northan, Nick Perry, Curtis Rueden, Johannes Schindelin,
 * Jean-Yves Tinevez and Michael Zinsmaier.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package net.imglib2.img.sparse;

import net.imglib2.img.basictypeaccess.IntAccess;

/**
 * IntAccess based on a {@link Ntree}{@code<Integer>}.
 *
 * @author Tobias Pietzsch
 */
public final class IntNtree implements IntAccess, NtreeAccess< Integer, IntNtree >
{

	private final long[] position;

	private final Ntree< Integer > data;

	/**
	 * Standard constructor called by factory
	 *
	 * @param dimensions
	 *            The dimensions of the tree
	 * @param value
	 *            Uniform value of created nodes of the tree
	 */
	public IntNtree( final long[] dimensions, final long[] position, final int value )
	{
		this.data = new Ntree< Integer >( dimensions, value );

		this.position = position;

	}

	/* Copy constructor */
	private IntNtree( final Ntree< Integer > data, final long[] position )
	{
		this.data = data;
		this.position = position;
	}

	@Override
	public int getValue( final int index )
	{
		// ignore index, get tree position from RandomAccess/Cursor
		return data.getNode( position ).getValue();
	}

	@Override
	public void setValue( final int index, final int value )
	{
		// ignore index, get tree position from RandomAccess/Cursor
		data.createNodeWithValue( position, value );
	}

	@Override
	public Ntree< Integer > getCurrentStorageNtree()
	{
		return data;
	}

	@Override
	public IntNtree createInstance( final long[] pos )
	{
		return new IntNtree( data, pos );
	}
}
