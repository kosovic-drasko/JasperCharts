export interface IGrafikon {
  id?: number;
  region?: string | null;
  prodaja?: number | null;
}

export class Grafikon implements IGrafikon {
  constructor(public id?: number, public region?: string | null, public prodaja?: number | null) {}
}

export function getGrafikonIdentifier(grafikon: IGrafikon): number | undefined {
  return grafikon.id;
}
