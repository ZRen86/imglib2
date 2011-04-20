/**
 * Copyright (c) 2009--2011, Stephan Saalfeld
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.  Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials
 * provided with the distribution.  Neither the name of the imglib project nor
 * the names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */
package mpicbg.imglib.position.transform;

import mpicbg.imglib.Localizable;
import mpicbg.imglib.Positionable;
import mpicbg.imglib.RealLocalizable;
import mpicbg.imglib.RealPositionable;

/**
 * Moves a {@link RealLocalizable} & {@link RealPositionable} and a
 * {@link Positionable} in synchrony.  The position of the latter is at the
 * round coordinates of the former:
 * 
 * f = r < 0 ? (long)( r - 0.5 ) : (long)( r + 0.5 )
 * 
 * @author Stephan Saalfeld <saalfeld@mpi-cbg.de>
 */
public class RealPositionableRoundPositionable< P extends RealLocalizable & RealPositionable > implements RealPositionable, RealLocalizable
{
	final protected P source;
	final protected Positionable target;
	
	final protected int n;
	
	final private long[] round;
	
	public RealPositionableRoundPositionable( final P source, final Positionable target )
	{
		this.source = source;
		this.target = target;
		
		n = source.numDimensions();
		
		round = new long[ n ];
	}
	

	/* EuclideanSpace */
	
	@Override
	public int numDimensions(){ return n; }

	
	/* RealPositionable */
	
	@Override
	public void move( final float distance, final int d )
	{
		source.move( distance, d );
		target.setPosition( Round.round( source.getDoublePosition( d ) ), d );
	}

	@Override
	public void move( final double distance, final int d )
	{
		source.move( distance, d );
		target.setPosition( Round.round( source.getDoublePosition( d ) ), d );
	}

	@Override
	public void move( final RealLocalizable localizable )
	{
		source.move( localizable );
		Round.round( source, round );
		target.setPosition( round );
	}

	@Override
	public void move( final float[] distance )
	{
		source.move( distance );
		Round.round( source, round );
		target.setPosition( round );
	}

	@Override
	public void move( final double[] distance )
	{
		source.move( distance );
		Round.round( source, round );
		target.setPosition( round );
	}

	@Override
	public void setPosition( final RealLocalizable localizable )
	{
		source.setPosition( localizable );
		Round.round( localizable, round );
		target.setPosition( round );
	}

	@Override
	public void setPosition( final float[] position )
	{
		source.setPosition( position );
		Round.round( position, round );
		target.setPosition( round );
	}

	@Override
	public void setPosition( final double[] position )
	{
		source.setPosition( position );
		Round.round( position, round );
		target.setPosition( round );
	}

	@Override
	public void setPosition( final float position, final int dim )
	{
		source.setPosition( position, dim );
		target.setPosition( Round.round( position ), dim );
	}

	@Override
	public void setPosition( final double position, final int dim )
	{
		source.setPosition( position, dim );
		target.setPosition( Round.round( position ), dim );
	}

	
	/* Positionable */
	
	@Override
	public void bck( final int dim )
	{
		source.bck( dim );
		target.bck( dim );
	}

	@Override
	public void fwd( final int dim )
	{
		source.fwd( dim );
		target.fwd( dim );
	}

	@Override
	public void move( final int distance, final int d )
	{
		source.move( distance, d );
		target.move( distance, d );
	}		

	@Override
	public void move( final long distance, final int d )
	{
		source.move( distance, d );
		target.move( distance, d );
	}

	@Override
	public void move( final Localizable localizable )
	{
		source.move( localizable );
		target.move( localizable );
	}

	@Override
	public void move( final int[] pos )
	{
		source.move( pos );
		target.move( pos );
	}

	@Override
	public void move( final long[] pos )
	{
		source.move( pos );
		target.move( pos );
	}
	
	@Override
	public void setPosition( final Localizable localizable )
	{
		source.setPosition( localizable );
		target.setPosition( localizable );
	}
	
	@Override
	public void setPosition( final int[] position )
	{
		source.setPosition( position );
		target.setPosition( position );
	}
	
	@Override
	public void setPosition( final long[] position )
	{
		source.setPosition( position );
		target.setPosition( position );
	}

	@Override
	public void setPosition( final int position, final int d )
	{
		source.setPosition( position, d );
		target.setPosition( position, d );
	}

	@Override
	public void setPosition( final long position, final int d )
	{
		source.setPosition( position, d );
		target.setPosition( position, d );
	}
	
	
	/* RealLocalizable */

	@Override
	public double getDoublePosition( final int d )
	{
		return source.getDoublePosition( d );
	}

	@Override
	public float getFloatPosition( final int d )
	{
		return source.getFloatPosition( d );
	}

	@Override
	public void localize( final float[] position )
	{
		source.localize( position );
	}

	@Override
	public void localize( final double[] position )
	{
		source.localize( position );
	}
}
