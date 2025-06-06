package com.gimnasio.demo;

import com.gimnasio.demo.Enums.RoleEnum;
import com.gimnasio.demo.Model.Security.PermissionEntity;
import com.gimnasio.demo.Model.Security.RoleEntity;
import com.gimnasio.demo.Model.Security.UserEntity;
import com.gimnasio.demo.Repository.UserRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepositorio userRepositorio) {
		return args -> {

			if (userRepositorio.count() > 0){return;}

			/* CREAR PERMISOS */

			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();

			/* CREAR ROLES */

			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, updatePermission, deletePermission, readPermission))
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission, updatePermission, readPermission))
					.build();

			RoleEntity roleClient = RoleEntity.builder()
					.roleEnum(RoleEnum.CLIENT)
					.permissionList(Set.of(createPermission, updatePermission, readPermission))
					.build();

			/* CREAR Usuarios */

			UserEntity userFabricio = UserEntity.builder()
					.username("fabricio")
					.password("$2a$10$6IPrNwOWORvcUFJf/peMguR/DJ2iQh7kvAwWo3JP4g1jjMeN38Bv6")
					.enabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userFranco = UserEntity.builder()
					.username("franco")
					.password("$2a$10$6IPrNwOWORvcUFJf/peMguR/DJ2iQh7kvAwWo3JP4g1jjMeN38Bv6")
					.enabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userLeandro = UserEntity.builder()
					.username("leandro")
					.password("$2a$10$6IPrNwOWORvcUFJf/peMguR/DJ2iQh7kvAwWo3JP4g1jjMeN38Bv6")
					.enabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userBautista = UserEntity.builder()
					.username("bautista")
					.password("$2a$10$6IPrNwOWORvcUFJf/peMguR/DJ2iQh7kvAwWo3JP4g1jjMeN38Bv6")
					.enabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userGonzalo = UserEntity.builder()
					.username("gonzalo")
					.password("$2a$10$6IPrNwOWORvcUFJf/peMguR/DJ2iQh7kvAwWo3JP4g1jjMeN38Bv6")
					.enabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			userRepositorio.saveAll(List.of(userFabricio,userFranco,userBautista,userGonzalo,userLeandro));
		};
	}
}
