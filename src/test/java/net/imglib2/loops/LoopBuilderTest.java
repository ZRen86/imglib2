/*
 * #%L
 * ImgLib2: a general-purpose, multidimensional image processing library.
 * %%
 * Copyright (C) 2009 - 2018 Tobias Pietzsch, Stephan Preibisch, Stephan Saalfeld,
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
package net.imglib2.loops;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import net.imglib2.Cursor;
import net.imglib2.Interval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.img.ImgFactory;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.img.cell.CellImgFactory;
import net.imglib2.img.planar.PlanarImgFactory;
import net.imglib2.test.ImgLib2Assert;
import net.imglib2.test.RandomImgs;
import net.imglib2.type.numeric.integer.IntType;
import net.imglib2.util.IntervalIndexer;
import net.imglib2.util.Intervals;
import net.imglib2.view.Views;

import org.junit.Test;

/**
 * Tests {@link LoopBuilder}.
 */
public class LoopBuilderTest
{

	@Test
	public void testLoopBuilderRun()
	{
		final RandomAccessibleInterval< IntType > imageA = randomImage( 1 );
		final RandomAccessibleInterval< IntType > imageB = randomImage( 42 );

		final RandomAccessibleInterval< IntType > sum = ArrayImgs.ints(
				Intervals.dimensionsAsLongArray( imageA ) );
		LoopBuilder.setImages( imageA, imageB, sum ).forEachPixel(
				( a, b, s ) -> {
					s.set( a.get() + b.get() );
				} );
		assertSum( imageA, imageB, sum );
	}

	private RandomAccessibleInterval< IntType > randomImage( final int randomSeed )
	{
		final Random random = new Random( randomSeed );
		final Img< IntType > result = RandomImgs.seed( random.nextInt() )
				.nextImage( new IntType(), 4, 2, 5 );
		return Views.translate( result, random.nextInt(), random.nextInt(), random.nextInt() );
	}

	private void assertSum( RandomAccessibleInterval< IntType > imageA, RandomAccessibleInterval< IntType > imageB, final RandomAccessibleInterval< IntType > sum )
	{
		final Cursor< IntType > a = Views.iterable( imageA ).cursor();
		final Cursor< IntType > b = Views.iterable( imageB ).cursor();
		final Cursor< IntType > s = Views.iterable( sum ).cursor();
		while ( s.hasNext() )
			assertEquals( s.next().get(), a.next().get() + b.next().get() );
	}

	@Test
	public void testFourConsumer()
	{
		List< Img< IntType > > images = createNImages( 4 );
		LoopBuilder.setImages( images.get( 0 ), images.get( 1 ), images.get( 2 ), images.get( 3 ) )
				.forEachPixel( ( a, b, c, d ) -> setIncreasing( a, b, c, d ) );
		assertIncreasing( images );
	}

	@Test
	public void testFiveConsumer()
	{
		List< Img< IntType > > images = createNImages( 5 );
		LoopBuilder.setImages( images.get( 0 ), images.get( 1 ), images.get( 2 ), images.get( 3 ), images.get( 4 ) )
				.forEachPixel( ( a, b, c, d, e ) -> setIncreasing( a, b, c, d, e ) );
		assertIncreasing( images );
	}

	@Test
	public void testSixConsumer()
	{
		List< Img< IntType > > images = createNImages( 6 );
		LoopBuilder.setImages( images.get( 0 ), images.get( 1 ), images.get( 2 ), images.get( 3 ), images.get( 4 ), images.get( 5 ) )
				.forEachPixel( ( a, b, c, d, e, f ) -> setIncreasing( a, b, c, d, e, f ) );
		assertIncreasing( images );
	}

	private List< Img< IntType > > createNImages( int n )
	{
		return IntStream.range( 0, n )
				.mapToObj( ignore -> ArrayImgs.ints( 1 ) ).collect( Collectors.toList() );
	}

	private void assertIncreasing( List< Img< IntType > > images )
	{
		for ( int i = 0; i < images.size(); i++ )
		{
			Img< IntType > image = images.get( i );
			assertEquals( i, image.firstElement().get() );
		}
	}

	private void setIncreasing( IntType... types )
	{
		for ( int i = 0; i < types.length; i++ )
			types[ i ].setInteger( i );
	}

	@Test
	public void testMultiThreaded()
	{
		Img< IntType > image = ArrayImgs.ints( 10, 10 );
		LoopBuilder.setImages( image ).multiThreaded().forEachPixel( IntType::inc );
		image.forEach( pixel -> assertEquals( 1, pixel.get() ) );
	}

	private final BiConsumer< IntType, IntType > COPY_ACTION = ( i, o ) -> o.set( i );

	@Test
	public void testRunUsingRandomAccessesOnSubInterval()
	{
		// setup
		final long[] dimensions = { 3, 2 };
		Img< IntType > input = ArrayImgs.ints( new int[] { 1, 2, 3, 4, 5, 6 }, dimensions );
		Img< IntType > expected = ArrayImgs.ints( new int[] { 0, 2, 0, 0, 5, 0 }, dimensions );
		Img< IntType > output = ArrayImgs.ints( dimensions );
		// process
		Interval interval = Intervals.createMinSize( 1, 0, 1, 2 );
		LoopBuilder.runOnChunkUsingRandomAccesses( new RandomAccessibleInterval[] { input, output }, COPY_ACTION, interval, false );
		// test
		ImgLib2Assert.assertImageEquals( expected, output );
	}

	@Test
	public void testRunOnChunkUsingCursors()
	{
		// setup
		final long[] dimensions = { 3, 2 };
		Img< IntType > input = ArrayImgs.ints( new int[] { 1, 2, 3, 4, 5, 6 }, dimensions );
		Img< IntType > output = ArrayImgs.ints( dimensions );
		Img< IntType > expected = ArrayImgs.ints( new int[] { 0, 2, 3, 4, 5, 0 }, dimensions );
		// process
		LoopBuilder.runOnChunkUsingCursors( Arrays.asList( input, output ), COPY_ACTION, 1, 4 );
		// test
		ImgLib2Assert.assertImageEquals( expected, output );
	}

	@Test
	public void testRunUsingCursorWithImagesOfDifferentIterationOrder()
	{
		final ImgFactory< IntType > array = new ArrayImgFactory<>( new IntType() );
		final ImgFactory< IntType > planar = new PlanarImgFactory<>( new IntType() );
		final ImgFactory< IntType > cellA = new CellImgFactory<>( new IntType(), 2, 1 );
		final ImgFactory< IntType > cellB = new CellImgFactory<>( new IntType(), 1, 2 );
		testRunUsingCursors( array, planar );
		testRunUsingCursors( array, cellB );
		testRunUsingCursors( cellA, cellB );
	}

	private void testRunUsingCursors( ImgFactory< IntType > inputImgFactory, ImgFactory< IntType > outputImgFactory )
	{
		final long[] dimensions = { 3, 2 };
		Img< IntType > input = inputImgFactory.create( dimensions );
		Img< IntType > output = outputImgFactory.create( dimensions );
		RandomImgs.seed( 42 ).randomize( input );
		LoopBuilder.setImages( input, output ).forEachPixel( COPY_ACTION );
		ImgLib2Assert.assertImageEquals( input, output );
	}

	@Test
	public void testFlatIterationOrder()
	{
		AtomicInteger ai = new AtomicInteger();
		Img< IntType > image = new CellImgFactory<>( new IntType(), 1, 2 ).create( 2, 2 );
		LoopBuilder.setImages( image ).flatIterationOrder().forEachPixel( pixel -> pixel.set( ai.incrementAndGet() ) );
		Img< IntType > expected = ArrayImgs.ints( new int[] { 1, 2, 3, 4 }, 2, 2 );
		ImgLib2Assert.assertImageEquals( expected, image );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testCheckDimensions() {
		RandomAccessibleInterval<IntType> imageA = ArrayImgs.ints( 10, 10 );
		RandomAccessibleInterval<IntType> imageB = ArrayImgs.ints( 10, 10, 2 );
		LoopBuilder.setImages( imageA, imageB ).forEachPixel( (a, b) -> {} );
	}

	@Test
	public void testLocalizing()
	{
		final RandomAccessibleInterval< IntType > imageA = ArrayImgs.ints( 7, 11 );
		final RandomAccessibleInterval< IntType > imageB = ArrayImgs.ints( 7, 11 );
		final long[] expected = new long[ 2 ];
		LoopBuilder.setImages( imageA, imageB ).forEachPixelLocalizing( ( a, b ) -> {
			final long x = a.getLongPosition( 0 ), y = a.getLongPosition( 1 );
			assertEquals( expected[ 0 ], x );
			assertEquals( expected[ 1 ], y );
			expected[ 0 ]++;
			if ( expected[ 0 ] == imageA.dimension( 0 ) )
			{
				expected[ 0 ] = 0;
				expected[ 1 ]++;
			}
		} );
	}

	@Test
	public void testLocalizingMultiThreaded()
	{
		final RandomAccessibleInterval< IntType > imageA = ArrayImgs.ints( 671, 1152 );
		final RandomAccessibleInterval< IntType > imageB = ArrayImgs.ints( 671, 1152 );
		final int[] visited = new int[ ( int ) Intervals.numElements( imageA ) ];
		LoopBuilder.setImages( imageA, imageB ).multiThreaded().forEachPixelLocalizing( ( a, b ) -> {
			final int index = ( int ) IntervalIndexer.positionToIndex( a, imageA );
			visited[ index ]++;
		} );
		assertTrue( IntStream.of( visited ).allMatch( v -> v == 1 ) );
	}
}
