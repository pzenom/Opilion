package es.induserco.opilion.datos;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;


// TODO: Auto-generated Javadoc
/**
 * Implements the generic CRUD data access operations using JPA APIs.
 * <p>
 * To write a DAO, subclass AND parameterize this class with your persistent class.
 * Of course, assuming that you have a traditional 1:1 approach for Entity:DAO design.
 * <p>
 * You have to inject a current JPA <tt>EntityManager</tt> to use a DAO.
 * Otherwise, this generic implementation will use
 * <tt>PersistentUtil.getCurrentEntotyManager()</tt> to obtain the current <tt>EntityManager</tt>.
 *
 * @param <T> the generic type
 * @param <ID> the generic type
 * @see JpaDAOFactory
 * @author Christian Bauer
 * @author adapted for JPA JSE by Alberto M.F.A.
 */
public abstract class GenericJpaDAO<T, ID extends Serializable>
        implements GenericDAO<T, ID> {

    /** The persistent class. */
    private Class<T> persistentClass;
    
    /** The em. */
    private EntityManager em;

    /**
     * Instantiates a new generic jpa dao.
     */
    @SuppressWarnings("unchecked")
	public GenericJpaDAO() {
        this.persistentClass = (Class<T>) 
        	((ParameterizedType) getClass().getGenericSuperclass())
        			.getActualTypeArguments()[0];
     }

    /**
     * Sets the entity manager.
     *
     * @param em the new entity manager
     */
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Gets the entity manager.
     *
     * @return the entity manager
     */
    protected EntityManager getEntityManager() {
        if (em == null){
            em = PersistenceUtil.getCurrentEntityManager();
        }
        return em;
    }

    /**
     * Gets the persistent class.
     *
     * @return the persistent class
     */
    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    /* (non-Javadoc)
     * @see es.induserco.opilion.datos.GenericDAO#findById(java.io.Serializable, boolean)
     */
    public T findById(ID id, boolean lock) {
        T entity;
        entity = (T) getEntityManager().find(getPersistentClass(), id);
        if (lock){
            getEntityManager().lock(entity, LockModeType.WRITE);
        }
        return entity;
    }

    /* (non-Javadoc)
     * @see es.induserco.opilion.datos.GenericDAO#persist(java.lang.Object)
     */
    public void persist(T entity) {
        getEntityManager().persist(entity);
    }

    /* (non-Javadoc)
     * @see es.induserco.opilion.datos.GenericDAO#remove(java.lang.Object)
     */
    public void remove(T entity) {
        getEntityManager().remove(entity);
    }

	/* (non-Javadoc)
	 * @see es.induserco.opilion.datos.GenericDAO#merge(java.lang.Object)
	 */
	//@Override
	public T merge(T entity) {
		return getEntityManager().merge(entity);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.datos.GenericDAO#refresh(java.lang.Object)
	 */
	//@Override
	public void refresh(T entity) {
		getEntityManager().refresh(entity);
	}

    /* (non-Javadoc)
     * @see es.induserco.opilion.datos.GenericDAO#flush()
     */
    public void flush() {
        getEntityManager().flush();
    }

    /* (non-Javadoc)
     * @see es.induserco.opilion.datos.GenericDAO#clear()
     */
    public void clear() {
        getEntityManager().clear();
    }
    
    /* (non-Javadoc)
     * @see es.induserco.opilion.datos.GenericDAO#findAll()
     */
    public List<T> findAll(){
    	String queryString = "SELECT x FROM " 
    		+ persistentClass.getSimpleName() 
    		+ " x";
    	return find(queryString);
    }

	/* (non-Javadoc)
	 * @see es.induserco.opilion.datos.GenericDAO#find(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String queryString) {
		return (List<T>)getEntityManager()
			.createQuery(queryString)
			.getResultList();
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.datos.GenericDAO#find(java.lang.String, java.lang.Object[])
	 */
	public List<T> find(String queryString, Object... values) {
		Query qry = getEntityManager().createQuery(queryString);
		return executeOrdinalParamsQuery(qry, values);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.datos.GenericDAO#findByNamedParams(java.lang.String, java.util.Map)
	 */
	public List<T> findByNamedParams(String queryString,
			Map<String, ? extends Object> params) {
		Query qry = getEntityManager().createQuery(queryString);
		return executeNominalParamsQuery(qry, params);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.datos.GenericDAO#findByNamedQuery(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(String queryName) {
		return (List<T>)getEntityManager()
			.createNamedQuery(queryName)
			.getResultList();
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.datos.GenericDAO#findByNamedQuery(java.lang.String, java.lang.Object[])
	 */
	public List<T> findByNamedQuery(String queryName, Object... values) {
		Query qry = getEntityManager().createNamedQuery(queryName);
		return executeOrdinalParamsQuery(qry, values);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.datos.GenericDAO#findByNamedQueryAndNamedParams(java.lang.String, java.util.Map)
	 */
	//@Override
	public List<T> findByNamedQueryAndNamedParams(String queryName,
			Map<String, ? extends Object> params) {
		Query qry = getEntityManager().createNamedQuery(queryName);
		return executeNominalParamsQuery(qry, params);
	}

	/**
	 * Execute ordinal params query.
	 *
	 * @param qry the qry
	 * @param values the values
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	private List<T> executeOrdinalParamsQuery(Query qry, Object... values) {
		int i = 1;
		for(Object arg: values){
			qry.setParameter(i++, arg);
		}
		return (List<T>) qry.getResultList();
	}

	/**
	 * Execute nominal params query.
	 *
	 * @param qry the qry
	 * @param params the params
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	private List<T> executeNominalParamsQuery(Query qry, 
			Map<String, ? extends Object> params) {
		for(String name: params.keySet()){
			qry.setParameter(name, params.get(name));
		}
		return (List<T>) qry.getResultList();
	}
}
