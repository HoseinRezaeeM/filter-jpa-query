@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("FROM Event e WHERE e.name like %:name%")
    Page<Event> findEventByNameIsLike(@Param("name") String name, Pageable pageable);

    @Query(value = "from Event e " +
            "where (:maxPoints IS NULL OR e.points <= :maxPoints)" +
            "AND (:minPoints IS NULL OR e.points >= :minPoints)" +
            "AND (:minRatePoints IS NULL OR e.ratePoints >= :minRatePoints)" +
            "AND (:maxRatePoints IS NULL OR e.ratePoints <= :maxRatePoints)" +
            "AND (:minExpirationPeriod IS NULL OR e.expirationPeriod >= :minExpirationPeriod)" +
            "AND (:maxExpirationPeriod IS NULL OR  e.expirationPeriod >= :maxExpirationPeriod)"+
            "AND (:isActive IS NULL OR  e.isActive = :isActive)")
    Page<Event> filterEvent(@Param("minPoints") Double minPoints,
                            @Param("maxPoints") Double maxPoints,
                            @Param("minRatePoints") Double minRatePoints,
                            @Param("maxRatePoints") Double maxRatePoints,
                            @Param("minExpirationPeriod") Integer mineExpirationPeriod,
                            @Param("maxExpirationPeriod") Integer maxExpirationPeriod,
                            @Param("isActive") Boolean isActive,
                            Pageable pageable);
}
