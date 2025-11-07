package com.gestor_de_proyectos.entity.enums;

import java.util.Set;

public enum Role {
	ADMIN(Set.of(Permissions.PROYECTO_READ, Permissions.PROYECTO_WRITE, Permissions.PROYECTO_DELETE)),
	USER(Set.of(Permissions.PROYECTO_READ));
	
	private final Set<Permissions> permissions;
	
	Role(Set<Permissions> permissions){
		this.permissions = permissions;
	}

	public Set<Permissions> getPermissions() {
		return permissions;
	}
}
