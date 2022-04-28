package app.igesa.entity;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import lombok.*;


@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="role")
public class Role extends Auditable<String> {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;

		@Enumerated(EnumType.STRING)
		@Column(length = 20)
		private ERole name;

		public Role() {
             super();
		}

		public Role(ERole name) {
			this.name = name;
		}

	public Role(String name) {
		this.name= ERole.valueOf(name);
	}


	public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public ERole getName() {
			return name;
		}

		public void setName(ERole name) {
			this.name = name;
		}
	}

