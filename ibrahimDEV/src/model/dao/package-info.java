
/**
 * Les DAOs (Data Access Objects) de ce package sont responsables de la communication
 * avec la base de données pour lire, écrire, mettre à jour et supprimer les données
 * associées aux entités de l'application.
 *
 * Chaque DAO correspond généralement à une entité métier spécifique et fournit des méthodes
 * pour exécuter des opérations CRUD (Create, Read, Update, Delete) sur les données de cette entité.
 *
 * Les DAOs encapsulent la logique d'accès aux données et offrent une interface abstraite
 * aux autres parties de l'application pour interagir avec la base de données. Ils utilisent
 * généralement des requêtes SQL ou des outils de mapping objet-relationnel (ORM) pour
 * communiquer avec la base de données de manière efficace et sécurisée.
 *
 * Les DAOs jouent un rôle crucial dans la séparation des préoccupations en isolant la logique
 * d'accès aux données de la logique métier de l'application. Cela permet une meilleure
 * maintenabilité, évolutivité et testabilité de l'application dans son ensemble.
 */
package model.dao;