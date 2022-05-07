package gestiunebug.repository;

public interface IRepository<ID, Entity> {
    /**
     * gaseste o entitate bazata pe id-ul acesteia
     * @param id - id-ul entitatii
     * @return entitatea cu id-ul dat
     */
    Entity getOne(ID id);

    /**
     * gaseste toate entitatile
     * @return toate entitatile
     */
    Iterable<Entity> getAll();

    /**
     * adauga o entitate
     * @param entity - entitatea ce trebuie adaugata; nu poate fi null
     * @return null daca entitatea a fost adaugata;
     *         entity daca exista deja o entitate cu acelasi id
     */
    Entity add(Entity entity);

    /**
     * sterge o entitate cu id-ul dat
     * @param id - id-ul entitatii care trebuie stearsa
     * @return entitatea stearsa
     *         null daca nu s-a gasit nicio entitate cu id-ul dat
     */
    Entity delete(ID id);

    /**
     * actualizeaza datele unei entitati
     * @param entity - entitatea cu datele actualizate; nu poate fi null
     * @return null daca entitatea a fost actualizata cu succes;
     *         entity daca nu exista nicio entitate cu acelasi id
     */
    Entity update(Entity entity);
}
