export interface Pagine<T> {
  content: T[],
  page: Page
}

export interface Page {
  totalElements: number,
  totalPage: number,
  number: number,
  size: number,
  first: boolean,
  last: boolean
}
