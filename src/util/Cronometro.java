package util;

public class Cronometro {
	/** The clock time when the timer started */
	private long startTicks;
	/** The ticks stored when the timer was paused */
	private long pausedTicks;
	/** The timer status */
	private boolean paused;
	private boolean started;

	// Initializes variables
	public Cronometro() {
		startTicks = 0;
		pausedTicks = 0;
		paused = false;
		started = false;
	}

	// The various clock actions
	public void start() {
		// Start the timer
		started = true;

		// Unpause the timer
		paused = false;

		// Get the current clock time
		startTicks = System.currentTimeMillis();
	}

	public void stop() {
		// Stop the timer
		started = false;

		// Unpause the timer
		paused = false;
	}

	public void pause() {
		// If the timer is running and isn't already paused
		if ((started == true) && (paused == false)) {
			// Pause the timer
			paused = true;

			// Calculate the paused ticks
			pausedTicks = System.currentTimeMillis() - startTicks;
		}
	}

	public void resume() {
		// If the timer is paused
		if (paused == true) {
			// Unpause the timer
			paused = false;

			// Reset the starting ticks
			startTicks = System.currentTimeMillis() - pausedTicks;

			// Reset the paused ticks
			pausedTicks = 0;
		}
	}

	// Gets the timer's time
	public long getTicks() {
		// If the timer is running
		if (started == true) {
			// If the timer is paused
			if (paused == true) {
				// Return the number of ticks when the timer was paused
				return pausedTicks;
			} else {
				// Return the current time minus the start time
				return System.currentTimeMillis() - startTicks;
			}
		}

		// If the timer isn't running
		return 0;
	}

	// Checks the status of the timer
	public boolean isStarted() {
		return started;
	}

	public boolean isPaused() {
		return paused;
	}
}
