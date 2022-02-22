export interface IReport {
  id?: number;
  region?: string | null;
  prodato?: number | null;
}

export class Report implements IReport {
  constructor(public id?: number, public region?: string | null, public prodato?: number | null) {}
}

export function getReportIdentifier(report: IReport): number | undefined {
  return report.id;
}
