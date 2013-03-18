/*
 * This project is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This project is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this project. If not, see <http://www.gnu.org/licenses/>.
 */
package com.cinchapi.concourse.store;

import com.cinchapi.concourse.services.ConcourseServiceProvider;
import com.cinchapi.concourse.services.ConcourseServiceTest;
import com.cinchapi.concourse.store.VolatileDatabase;

/**
 * 
 * 
 * @author jnelson
 */
public class VolatileDatabaseTest extends ConcourseServiceTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cinchapi.concourse.ConcourseServiceTest#getService()
	 */
	@Override
	protected VolatileDatabase getService() {
		return ConcourseServiceProvider.provideVolatileDatabase();
	}

}
