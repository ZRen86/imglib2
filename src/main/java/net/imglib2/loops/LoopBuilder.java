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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.LongConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.imglib2.Cursor;
import net.imglib2.Dimensions;
import net.imglib2.FinalInterval;
import net.imglib2.Interval;
import net.imglib2.IterableInterval;
import net.imglib2.Positionable;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.array.AbstractArrayCursor;
import net.imglib2.img.cell.CellCursor;
import net.imglib2.img.planar.PlanarCursor;
import net.imglib2.util.Intervals;
import net.imglib2.view.Views;
import net.imglib2.view.iteration.SlicingCursor;

/**
 * {@link LoopBuilder} provides an easy way to write fast loops on
 * {@link RandomAccessibleInterval}s. For example, this is a loop that
 * calculates the sum of two images:
 * <p>
 * <pre>
 * {@code
 * RandomAccessibleInterval<DoubleType> imageA = ...
 * RandomAccessibleInterval<DoubleType> imageB = ...
 * RandomAccessibleInterval<DoubleType> sum = ...
 *
 * LoopBuilder.setImages(imageA, imageB, sum).forEachPixel(
 *     (a, b, s) -> {
 *          s.setReal(a.getRealDouble() + b.getRealDouble());
 *     }
 * );
 * }
 * </pre>
 * <p>
 * The {@link RandomAccessibleInterval}s {@code imageA}, {@code imageB} and
 * {@code sum} must have equal dimensions, but the bounds of there
 * {@link Intervals} can differ.
 *
 * @author Matthias Arzt
 * @param <T> Action type of pixelwise operation, {@link #forEachPixel without localization}.
 * @param <LT> Action type of pixelwise operation, {@link #forEachPixelLocalizing with localization}.
 */
public class LoopBuilder< T, LT >
{

	// fields

	private final Dimensions dimensions;

	private final RandomAccessibleInterval< ? >[] images;

	private MultiThreadSetting multiThreaded = MultiThreadSetting.SINGLE;

	private boolean useFlatIterationOrder = false;

	// public methods

	/**
	 * @see LoopBuilder
	 */
	public static < A > LoopBuilder< Consumer< A >, Consumer< RandomAccess< A > > > setImages( final RandomAccessibleInterval< A > a )
	{
		return new LoopBuilder<>( a );
	}

	/**
	 * @see LoopBuilder
	 */
	public static < A, B > LoopBuilder< BiConsumer< A, B >, BiConsumer< RandomAccess< A >, RandomAccess< B > > > setImages( final RandomAccessibleInterval< A > a, final RandomAccessibleInterval< B > b )
	{
		return new LoopBuilder<>( a, b );
	}

	/**
	 * @see LoopBuilder
	 */
	public static < A, B, C > LoopBuilder< TriConsumer< A, B, C >, TriConsumer< RandomAccess< A >, RandomAccess< B >, RandomAccess< C > > > setImages( final RandomAccessibleInterval< A > a, final RandomAccessibleInterval< B > b, final RandomAccessibleInterval< C > c )
	{
		return new LoopBuilder<>( a, b, c );
	}

	/**
	 * @see LoopBuilder
	 */
	public static < A, B, C, D > LoopBuilder< FourConsumer< A, B, C, D >, FourConsumer< RandomAccess< A >, RandomAccess< B >, RandomAccess< C >, RandomAccess< D > > > setImages( final RandomAccessibleInterval< A > a, final RandomAccessibleInterval< B > b, final RandomAccessibleInterval< C > c, final RandomAccessibleInterval< D > d )
	{
		return new LoopBuilder<>( a, b, c, d );
	}

	/**
	 * @see LoopBuilder
	 */
	public static < A, B, C, D, E > LoopBuilder< FiveConsumer< A, B, C, D, E >, FiveConsumer< RandomAccess< A >, RandomAccess< B >, RandomAccess< C >, RandomAccess< D >, RandomAccess< E > > > setImages( final RandomAccessibleInterval< A > a, final RandomAccessibleInterval< B > b, final RandomAccessibleInterval< C > c, final RandomAccessibleInterval< D > d, final RandomAccessibleInterval< E > e )
	{
		return new LoopBuilder<>( a, b, c, d, e );
	}

	/**
	 * @see LoopBuilder
	 */
	public static < A, B, C, D, E, F > LoopBuilder< SixConsumer< A, B, C, D, E, F >, SixConsumer< RandomAccess< A >, RandomAccess< B >, RandomAccess< C >, RandomAccess< D >, RandomAccess< E >, RandomAccess< F > > > setImages( final RandomAccessibleInterval< A > a, final RandomAccessibleInterval< B > b, final RandomAccessibleInterval< C > c, final RandomAccessibleInterval< D > d, final RandomAccessibleInterval< E > e, final RandomAccessibleInterval< F > f )
	{
		return new LoopBuilder<>( a, b, c, d, e, f );
	}

	/**
	 * @see LoopBuilder
	 */
	public void forEachPixel( final T action )
	{
		Objects.requireNonNull( action );
		if ( Intervals.numElements( dimensions ) == 0 )
			return;
		List< IterableInterval< ? > > iterableIntervals = imagesAsIterableIntervals();
		if ( allCursorsAreFast( iterableIntervals ) )
			runUsingCursors( iterableIntervals, action );
		else
			runUsingRandomAccesses( action, false );
	}

	public void forEachPixelLocalizing( final LT action )
	{
		Objects.requireNonNull( action );
		if ( Intervals.numElements( dimensions ) == 0 )
			return;
		runUsingRandomAccesses( action, true );
	}

	private boolean allCursorsAreFast( List<IterableInterval<?>> iterableIntervals )
	{
		return iterableIntervals.stream().allMatch( this::cursorIsFast );
	}

	private boolean cursorIsFast( IterableInterval<?> image )
	{
		Cursor< ? > cursor = image.cursor();
		return cursor instanceof AbstractArrayCursor ||
				cursor instanceof SlicingCursor ||
				cursor instanceof PlanarCursor ||
				cursor instanceof CellCursor;
	}

	/**
	 * By default {@link LoopBuilder} runs the loop without multi-threading.
	 * Calling this method causes LoopBuilder to use multi-threading to speed up the operation.
	 * <p>
	 * WARNING: You need to make sure that your operation is thread safe.
	 */
	public LoopBuilder< T, LT > multiThreaded()
	{
		this.multiThreaded = Objects.requireNonNull( MultiThreadSetting.MULTI );
		return this;
	}

	/**
	 * {@link LoopBuilder} might use any iteration order to execute
	 * the loop. Calling this method will cause {@link LoopBuilder}
	 * to use flat iteration order, when executing the loop.
	 * <p>
	 * WARNING: Don't use multi-threading if you want to have flat
	 * iteration order.
	 */
	public LoopBuilder< T, LT > flatIterationOrder()
	{
		return this.flatIterationOrder( true );
	}

	/**
	 * If false, {@link LoopBuilder} might use any iteration order
	 * to execute the loop.
	 * <p>
	 * If true, {@link LoopBuilder} will use
	 * flat iteration order, and multi threading is disabled.
	 * <p>
	 * WARNING: Don't use multi-threading if you want to have flat
	 * iteration order.
	 *
	 * @see net.imglib2.FlatIterationOrder
	 */
	public LoopBuilder< T, LT > flatIterationOrder( boolean value )
	{
		this.useFlatIterationOrder = value;
		return this;
	}

	public interface TriConsumer< A, B, C >
	{
		void accept( A a, B b, C c );
	}

	public interface FourConsumer< A, B, C, D >
	{
		void accept( A a, B b, C c, D d );
	}

	public interface FiveConsumer< A, B, C, D, E >
	{
		void accept( A a, B b, C c, D d, E e );
	}

	public interface SixConsumer< A, B, C, D, E, F >
	{
		void accept( A a, B b, C c, D d, E e, F f );
	}

	// Helper methods

	private LoopBuilder( final RandomAccessibleInterval< ? >... images )
	{
		this.images = images;
		this.dimensions = new FinalInterval( images[ 0 ] );
		checkDimensions();
	}

	private void checkDimensions()
	{
		final long[] dims = Intervals.dimensionsAsLongArray( dimensions );
		final boolean equal = Stream.of( images ).allMatch( image -> Arrays.equals( dims, Intervals.dimensionsAsLongArray( image ) ) );
		if ( !equal )
		{
			StringJoiner joiner = new StringJoiner( ", " );
			for ( Interval interval : images )
				joiner.add( Arrays.toString( Intervals.dimensionsAsLongArray( interval ) ) );
			throw new IllegalArgumentException( "LoopBuilder, image dimensions do not match: " + joiner + "." );
		}
	}

	void runUsingRandomAccesses( Object action, boolean localizing )
	{
		final int nTasks = multiThreaded.suggestNumberOfTasks();
		final Interval interval = new FinalInterval( dimensions );
		final List< Interval > chunks = IntervalChunks.chunkInterval( interval, nTasks );
		multiThreaded.forEach( chunks, chunk -> runOnChunkUsingRandomAccesses( images, action, chunk, localizing ) );
	}

	static void runOnChunkUsingRandomAccesses( RandomAccessibleInterval<?>[] images, Object action, Interval subInterval, boolean localizing )
	{
		final List< RandomAccess< ? > > samplers = Stream.of( images ).map( LoopBuilder::initRandomAccess ).collect( Collectors.toList() );
		final Positionable synced = SyncedPositionables.create( samplers );
		if ( !Views.isZeroMin( subInterval ) )
			synced.move( Intervals.minAsLongArray( subInterval ) );
		final Runnable runnable = BindActionToSamplers.bindActionToSamplers( action, samplers, localizing );
		LoopUtils.createIntervalLoop( synced, subInterval, runnable ).run();
	}

	private static RandomAccess< ? > initRandomAccess( final RandomAccessibleInterval< ? > image )
	{
		final RandomAccess< ? > ra = image.randomAccess();
		ra.setPosition( Intervals.minAsLongArray( image ) );
		return ra;
	}

	void runUsingCursors( T action )
	{
		runUsingCursors( imagesAsIterableIntervals(), action );
	}

	private List< IterableInterval< ? > > imagesAsIterableIntervals()
	{
		return useFlatIterationOrder ?
				flatIterableIntervals() :
				equalIterationOrderIterableIntervals();
	}

	private void runUsingCursors( List< IterableInterval< ? > > iterableIntervals, T action )
	{
		int nTasks = multiThreaded.suggestNumberOfTasks();
		final FinalInterval indices = new FinalInterval( Intervals.numElements( images[ 0 ] ) );
		List< Interval > chunks = IntervalChunks.chunkInterval( indices, nTasks );
		multiThreaded.forEach( chunks, chunk ->
				runOnChunkUsingCursors( iterableIntervals, action, chunk.min( 0 ), chunk.dimension( 0 ) ) );
	}

	static void runOnChunkUsingCursors( List< IterableInterval< ? > > iterableIntervals, Object action, long offset, long numElements )
	{
		final List< Cursor< ? > > cursors = iterableIntervals.stream().map( IterableInterval::cursor ).collect( Collectors.toList() );
		if ( offset != 0 )
			jumpFwd( cursors, offset );
		LongConsumer cursorLoop = FastCursorLoops.createLoop( action, cursors );
		cursorLoop.accept( numElements );
	}

	private static void jumpFwd( List< Cursor< ? > > cursors, long offset )
	{
		for ( Cursor< ? > cursor : cursors )
			cursor.jumpFwd( offset );
	}

	private List< IterableInterval< ? > > equalIterationOrderIterableIntervals()
	{
		List< IterableInterval< ? > > iterableIntervals = Stream.of( images ).map( Views::iterable ).collect( Collectors.toList() );
		List< Object > iterationOrders = iterableIntervals.stream().map( IterableInterval::iterationOrder ).collect( Collectors.toList() );
		if ( allEqual( iterationOrders ) )
			return iterableIntervals;
		return flatIterableIntervals();
	}

	private List< IterableInterval< ? > > flatIterableIntervals()
	{
		return Stream.of( images ).map( Views::flatIterable ).collect( Collectors.toList() );
	}

	private static boolean allEqual( List< Object > values )
	{
		Object first = values.get( 0 );
		return values.stream().allMatch( first::equals );
	}
}
