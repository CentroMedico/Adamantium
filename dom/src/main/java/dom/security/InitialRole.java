package dom.security;

import org.apache.isis.applib.fixturescripts.FixtureResult;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;
import org.isisaddons.module.security.dom.permission.ApplicationPermissionMode;
import org.isisaddons.module.security.dom.permission.ApplicationPermissionRule;
import org.isisaddons.module.security.seed.scripts.AbstractRoleAndPermissionsFixtureScript;

public class InitialRole extends AbstractRoleAndPermissionsFixtureScript {

	public static final String ROLE_NAME = "isis-module-security-regular-user";

	public InitialRole() {
		super(ROLE_NAME, "Pacientes");
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		newClassPermissions(ApplicationPermissionRule.ALLOW,
				ApplicationPermissionMode.CHANGING, FixtureResult.class);
	}
}
