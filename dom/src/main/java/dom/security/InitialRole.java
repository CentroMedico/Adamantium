/*
 Copyright 2015 Adamantium

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package dom.security;

import org.apache.isis.applib.fixturescripts.FixtureResult;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;
import org.isisaddons.module.security.dom.permission.ApplicationPermissionMode;
import org.isisaddons.module.security.dom.permission.ApplicationPermissionRule;
import org.isisaddons.module.security.seed.scripts.AbstractRoleAndPermissionsFixtureScript;

public class InitialRole extends AbstractRoleAndPermissionsFixtureScript {

	public static final String ROLE_NAME = "isis-pacientes";

	public InitialRole() {
		super(ROLE_NAME, "Pacientes");
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		newClassPermissions(ApplicationPermissionRule.ALLOW,
				ApplicationPermissionMode.CHANGING, FixtureResult.class);
	}
}
