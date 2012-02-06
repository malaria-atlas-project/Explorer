package uk.ac.ox.map.explorer.client.filter.presenter;

/**
 * The operators a {@link Filter} can use.
 * Not all have been implemented server-side yet.
 */
public enum Operator {
  eq, contains, like, lt, gt, le, ge
}
