#include <bits/stdc++.h>
using namespace std
vector<vector<string>> ed;
vector<pair<char, string>> a, b;
int n;
char start;
bool d[30][100][100];
int  ind[200][200];
bool tmp[60][100][100][10];

int main()
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  file();

    string s, next, t;

  cin >> n >> start;
  getline(cin, s);

  char f;
  string c;

  ed.resize(200);

  for (int i = 0; i < n; i++)
  {
    getline(cin, s);
    t = "";
    f = s[0];

    if (s[s.size() - 1] != '>')
      for (int j = 5; j < s.size(); j++)
        t += s[j];

    a.push_back({ f, t });
    ed[f - 'A'].push_back(t);
    ind[f - 'A'][ed[f - 'A'].size() - 1] = a.size() - 1;
  }

  cin >> s;

  for(int i = 0; i < s.size(); i++)
    for (int j = 0; j < a.size(); j++)
    {
      d[a[j].first - 'A'][i][i + 1] = a[j].second.size() == 1 && a[j].second[0] == s[i];
      d[a[j].first - 'A'][i][i] = a[j].second.empty();
      tmp[j][i][i][0] = 1;
    }

  for(int c = 0; c < 30; c++)
    for(int g = 0; g <= s.size(); g++)
      for (int i = 0; i < s.size(); i++)
      {
        int j = i + g;
        
        if (j > s.size())
          break;

        for (int k = 1; k <= 5; k++)
          for (int w = 0; w < a.size(); w++)
          {
            if (a[w].second.size() < k)
              continue;

            for (int e = i; e <= j; e++)
              if (a[w].second[k - 1] >= 'a' && a[w].second[k - 1] <= 'z')
                tmp[w][i][j][k] |= tmp[w][i][e][k - 1] && (e == j - 1) && (s[e] == a[w].second[k - 1]);
              else
                tmp[w][i][j][k] |= tmp[w][i][e][k - 1] && d[a[w].second[k - 1] - 'A'][e][j];
          }

        for (int w = 0; w < 26; w++)
          for (int w1 = 0; w1 < ed[w].size(); w1++)
            d[w][i][j] |= tmp[ind[w][w1]][i][j][ed[w][w1].size()];
      }

  if (d[start - 'A'][0][s.size()])
    cout << "yes";
  else
    cout << "no";
  return 0;
}