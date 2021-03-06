/*
 * Copyright (c) 2011, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package org.glassfish.grizzly.http.server;

import java.io.IOException;

import org.glassfish.grizzly.Buffer;
import org.glassfish.grizzly.Cacheable;
import org.glassfish.grizzly.ReadHandler;
import org.glassfish.grizzly.http.io.InputBuffer;
import org.glassfish.grizzly.http.io.NIOInputStream;

/**
 * {@link NIOInputStream} implementation based on {@link InputBuffer}.
 *
 * @author Ryan Lubke
 * @author Alexey Stashok
 */
final class NIOInputStreamImpl extends NIOInputStream implements Cacheable {

    private InputBuffer inputBuffer;

    // ------------------------------------------------ Methods from InputStream

    /**
     * {@inheritDoc}
     */
    @Override
    public int read() throws IOException {
        return inputBuffer.readByte();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int read(byte[] b) throws IOException {
        return inputBuffer.read(b, 0, b.length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return inputBuffer.read(b, off, len);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long skip(long n) throws IOException {
        return inputBuffer.skip(n);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int available() throws IOException {
        return inputBuffer.available();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws IOException {
        inputBuffer.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mark(int readlimit) {
        inputBuffer.mark(readlimit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() throws IOException {
        inputBuffer.reset();
    }

    /**
     * This {@link NIOInputStream} implementation supports marking.
     *
     * @return <code>true</code>
     */
    @Override
    public boolean markSupported() {
        return inputBuffer.markSupported();
    }

    // --------------------------------------------- Methods from InputSource

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyAvailable(ReadHandler handler) {
        inputBuffer.notifyAvailable(handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyAvailable(ReadHandler handler, int size) {
        inputBuffer.notifyAvailable(handler, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinished() {
        return inputBuffer.isFinished();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int readyData() {
        return inputBuffer.available();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isReady() {
        return inputBuffer.available() > 0;
    }

    // --------------------------------------- Methods from BinaryNIOInputSource

    /**
     * {@inheritDoc}
     */
    @Override
    public Buffer getBuffer() {
        return inputBuffer.getBuffer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Buffer readBuffer() {
        return inputBuffer.readBuffer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Buffer readBuffer(final int size) {
        return inputBuffer.readBuffer(size);
    }
    // -------------------------------------------------- Methods from Cacheable

    /**
     * {@inheritDoc}
     */
    @Override
    public void recycle() {

        inputBuffer = null;

    }

    // ---------------------------------------------------------- Public Methods

    public void setInputBuffer(final InputBuffer inputBuffer) {

        this.inputBuffer = inputBuffer;

    }
}
