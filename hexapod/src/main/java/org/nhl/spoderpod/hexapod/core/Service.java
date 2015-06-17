package org.nhl.spoderpod.hexapod.core;

import java.util.Arrays;
import java.util.List;

import org.nhl.spoderpod.hexapod.interfaces.I_Component;
import org.nhl.spoderpod.hexapod.interfaces.I_Threaded;

/**
 * The service class is an aggregate of components. It also has the mailbox for the components and updates all the components.
 * @author achmed
 *
 */
public final class Service implements I_Threaded {
	private final ComponentRef self;
	private final Thread thread;
	private final MessageBus messageBus;
	private final List<I_Component> components;
	private volatile boolean running;

	/**
	 * @param name The name of the service
	 * @param components The components that this service should consist of.
	 */
	public Service(String name, I_Component[] components) {
		this.self = new ComponentRef(name);
		this.thread = new Thread(this);
		this.messageBus = new MessageBus();
		this.components = Arrays.asList(components);
	}

	public void start() {
		if (!this.running) {
			this.thread.start();
		}
	}

	public void stop() {
		this.running = false;
	}

	/**
	 * Initialize the service and all of its components.
	 */
	private void init() {
		for (I_Component component : this.components) {
			this.messageBus.addComponent(component.getSelf());
			component.init(this.messageBus);
		}
	}

	/**
	 * Close the service and all its components.
	 */
	private void close() {
		for (I_Component component : this.components) {
			component.close(this.messageBus);
		}
	}

	/**
	 * Update all components.
	 */
	private void tick() {
		for (I_Component component : this.components) {
			component.update(this.messageBus);
		}
	}

	public void run() {
		if (this.running || Thread.currentThread() != this.thread) {
			return;
		}
		init();
		this.running = true;
		while (this.running) {
			tick();
		}
		close();
	}
}
