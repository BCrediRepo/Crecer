@CrecerLogin
Feature: Se accede a la plataforma
		Se utiliza para reiterar los accesos

  @tag1
  Scenario: Login a Crecer
    Given La URL de Crecer
    When Ingreso credenciales
    Then Accedo al mainpage del sistema

#    Examples: 
#      | name  | value | status  |
#      | name1 |     5 | success |
#      | name2 |     7 | Fail    |

      